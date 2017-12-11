package com.thomascantie.insa.model.network.service;

import com.thomascantie.insa.model.network.util.LocalNetworkIPAddress;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Classe de service reposant sur le protocole de transport UDP pour recevoir des messages
 *
 * @author Thomas cantié
 * @author Andy Piszyna
 * @see MessageReceiverService
 */
public class UDPMessageReceiverService implements MessageReceiverService {

	/**
	 * Mise en écoute des messages entrants
	 *
	 * @param port                    Le port sur lequel écouter
	 * @param incomingMessageListener Le déclencheur des messages entrants
	 * @throws Exception si une erreur système survient
	 */
	@Override
	public void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception {
		DatagramSocket socket = new DatagramSocket(port);
		new Thread(new WaitingConnections(socket, incomingMessageListener)).start();
	}

}

/**
 * Thread de traitement des messages entrants
 *
 * @author Thomas cantié
 * @author Andy Piszyna
 * @see Runnable
 */
class WaitingConnections implements Runnable {

	/**
	 * Socket d'écoute
	 */
	private DatagramSocket socket;
	/**
	 * Déclencheur des messages entrants
	 */
	private IncomingMessageListener listener;

	/**
	 * Constructeur
	 *
	 * @param socket   La socket d'écoute
	 * @param listener Le déclencheur sur les messages entrants
	 */
	public WaitingConnections(DatagramSocket socket, IncomingMessageListener listener) {
		this.socket = socket;
		this.listener = listener;
	}

	@Override
	public void run() {
		try {
			while (true) {
				byte[] buffer = new byte[8196];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				this.socket.receive(packet); // block current thread until a datagram is received
				InetAddress ipAddress = packet.getAddress();

				if (!ipAddress.getHostAddress().equals(LocalNetworkIPAddress.getLocalIPAddress())) {
					this.listener.onNewIncomingMessage(ipAddress, new String(buffer));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}