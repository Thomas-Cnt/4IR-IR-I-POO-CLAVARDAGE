package com.thomascantie.insa.model.core;

import com.thomascantie.insa.controler.UpdateConnection;
import com.thomascantie.insa.model.network.service.UDPBroadcastSenderService;
import com.thomascantie.insa.model.network.service.UDPMessageReceiverService;
import com.thomascantie.insa.model.network.util.LocalNetworkBroadcastAddress;
import com.thomascantie.insa.view.ViewConnections;

import java.net.InetAddress;
import java.util.*;

public class ConnectionsManager {

	private static ConnectionsManager instance;
	private User user;
	private Map<String, ConnectionInfo> map;
	private ViewConnections view;

	private ConnectionsManager() {
		this.map = new HashMap<String, ConnectionInfo>();
	}

	public static synchronized ConnectionsManager getInstance() {
		if (instance == null)
			instance = new ConnectionsManager();
		return instance;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setViewConnections(ViewConnections view) {
		this.view = view;
	}

	public void updateConnexionInfo(String pseudo, InetAddress addr, int port) {
		this.map.put(pseudo, new ConnectionInfo(addr.getHostAddress(), port));
	}

	public void removeConnectionInfo(String pseudo) {
		this.map.remove(pseudo);
	}

	public boolean contains(String pseudo) {
		return this.map.containsKey(pseudo);
	}

	public ConnectionInfo getConnexionInfoFor(String pseudo) {
		return this.map.get(pseudo);
	}

	public List<String> getAllPseudos() {
		List<String> list = new ArrayList<String>();
		Iterator<String> it = this.map.keySet().iterator();
		while(it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

	public String getPseudoAtIP(InetAddress ip) {
		for (Map.Entry<String, ConnectionInfo> entry : this.map.entrySet()) {
			if (entry.getValue().getIPAddress().equals(ip.getHostAddress())) {
				return entry.getKey();
			}
		}
		return null;
	}

	public int getPortAtIP(InetAddress ip) {
		for (ConnectionInfo info : this.map.values()) {
			if (info.getIPAddress().equals(ip.getHostAddress())) {
				return info.getPortNumber();
			}
		}
		return -1;
	}

	public void notifyConnectionOn() throws Exception {
		new UDPBroadcastSenderService().sendMessageOn(LocalNetworkBroadcastAddress.getBroadcastIPAddress(), 1234, new ConnectionMessage(this.user, this.view.getLocalPortNumber(), State.ON).toString());
	}

	public void notifyConnectionOff() throws Exception {
		new UDPBroadcastSenderService().sendMessageOn(LocalNetworkBroadcastAddress.getBroadcastIPAddress(), 1234, new ConnectionMessage(this.user, this.view.getLocalPortNumber(), State.OFF).toString());
	}

	public void listeningNotifications() throws Exception {
		new UDPMessageReceiverService().listenOnPort(1234, new UpdateConnection(this.view));
	}

}
