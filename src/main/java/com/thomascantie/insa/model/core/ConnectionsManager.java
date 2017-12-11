package com.thomascantie.insa.model.core;

import com.thomascantie.insa.controler.UpdateConnection;
import com.thomascantie.insa.model.network.service.UDPBroadcastSenderService;
import com.thomascantie.insa.model.network.service.UDPMessageReceiverService;
import com.thomascantie.insa.model.network.util.LocalNetworkBroadcastAddress;
import com.thomascantie.insa.view.ViewConnections;

import java.net.InetAddress;
import java.util.*;

/**
 * Classe de gestion des connexions courantes
 *
 * @author Thomas Cantié
 * @author Andy Piszyna
 * @see User
 * @see ConnectionInfo
 * @see ViewConnections
 */
public class ConnectionsManager {

	/**
	 * Constante du numéro de port utilisé pour les connexions
	 */
	private static final int CONNEXION_PORT = 1234;
	/**
	 * Instance courante du gestionnaire
	 */
	private static ConnectionsManager instance;
	/**
	 * Utilisateur local
	 */
	private User user;
	/**
	 * Table de corresoindance pseudo <-> informations de connexion
	 */
	private Map<String, ConnectionInfo> map;
	/**
	 * Panneau de visualisation des connexions
	 */
	private ViewConnections view;

	/**
	 * Constructeur
	 */
	private ConnectionsManager() {
		this.map = new HashMap<String, ConnectionInfo>();
	}

	/**
	 * Accesseur en lecture
	 *
	 * @return l'instance courante du gestionnaire
	 */
	public static synchronized ConnectionsManager getInstance() {
		if (instance == null)
			instance = new ConnectionsManager();
		return instance;
	}

	/**
	 * Accesseur en écriture
	 *
	 * @param user l'utilisateur du gestionnaire
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Accesseur en écriture
	 *
	 * @param view le panneau de visualisation des connexions
	 */
	public void setViewConnections(ViewConnections view) {
		this.view = view;
	}

	/**
	 * Modificateur
	 * Ajoute une nouvelle connexion
	 *
	 * @param pseudo le pseudo de l'utilisateur distant
	 * @param addr   l'adresse distante
	 * @param port   le numéro de port distant
	 */
	public void updateConnexionInfo(String pseudo, InetAddress addr, int port) {
		this.map.put(pseudo, new ConnectionInfo(addr.getHostAddress(), port));
	}

	/**
	 * Modificateur
	 * Supprime une connexion
	 *
	 * @param pseudo le pseudo de la connexion à supprimer
	 */
	public void removeConnectionInfo(String pseudo) {
		this.map.remove(pseudo);
	}

	/**
	 * Evaluateur
	 *
	 * @param pseudo le pseudo associé à la connexion à rechercher
	 * @return true si la connexion existe ; false
	 */
	public boolean contains(String pseudo) {
		return this.map.containsKey(pseudo);
	}

	/**
	 * Accesseur en lecture
	 *
	 * @param pseudo le pseudo associé à la connexion à rechercher
	 * @return les informations de connexion
	 */
	public ConnectionInfo getConnexionInfoFor(String pseudo) {
		return this.map.get(pseudo);
	}

	/**
	 * Accesseur en lecture
	 *
	 * @return tous les pseuds associés aux connexions
	 */
	public List<String> getAllPseudos() {
		List<String> list = new ArrayList<String>();
		Iterator<String> it = this.map.keySet().iterator();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

	/**
	 * Accesseur en lecture
	 *
	 * @param ip l'adresse IP à rechercher
	 * @return le pseudo de la connexion associée à l'adresse IP ; null s'il n'existe pas
	 */
	public String getPseudoAtIP(InetAddress ip) {
		for (Map.Entry<String, ConnectionInfo> entry : this.map.entrySet()) {
			if (entry.getValue().getIPAddress().equals(ip.getHostAddress())) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * Accesseur en lecture
	 *
	 * @param ip l'adresse IP à rechercher
	 * @return le numéro de port de la connexion associe=ée à l'adresse IP ; null s'il n'existe pas
	 */
	public int getPortAtIP(InetAddress ip) {
		for (ConnectionInfo info : this.map.values()) {
			if (info.getIPAddress().equals(ip.getHostAddress())) {
				return info.getPortNumber();
			}
		}
		return -1;
	}

	/**
	 * Notification de l'ouverture de la connexion
	 *
	 * @throws Exception si une erreur système survient
	 * @see UDPBroadcastSenderService
	 */
	public void notifyConnectionOn() throws Exception {
		new UDPBroadcastSenderService().sendMessageOn(LocalNetworkBroadcastAddress.getBroadcastIPAddress(), 1234, new ConnectionMessage(this.user, this.view.getLocalPortNumber(), State.ON).toString());
	}

	/**
	 * Notification de la fermeture de la connexion
	 *
	 * @throws Exception si une erreur système survient
	 * @see UDPBroadcastSenderService
	 */
	public void notifyConnectionOff() throws Exception {
		new UDPBroadcastSenderService().sendMessageOn(LocalNetworkBroadcastAddress.getBroadcastIPAddress(), 1234, new ConnectionMessage(this.user, this.view.getLocalPortNumber(), State.OFF).toString());
	}

	/**
	 * Mise en écoute des connexions courantes
	 *
	 * @throws Exception si une erreur système survient
	 * @see UDPMessageReceiverService
	 */
	public void listeningNotifications() throws Exception {
		new UDPMessageReceiverService().listenOnPort(CONNEXION_PORT, new UpdateConnection(this.view));
	}

}
