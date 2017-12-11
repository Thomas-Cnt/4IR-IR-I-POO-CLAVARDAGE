package com.thomascantie.insa.model.network.service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Classe de service reposant sur le protocole de transport UDP pour envoyer un message en broadcasr
 *
 * @author Thomas cantié
 * @author Andy Piszyna
 * @see MessageSenderService
 */
public class UDPBroadcastSenderService implements MessageSenderService {

	/**
	 * 	 * Envoie un message via le réseau

	 * @param broadcastIPAddress
	 * @param port      Le numéro de port sur lequel envoyer le message
	 * @param message   Le message à envoyer via le réseau
	 * @throws Exception
	 */
	@Override
	public void sendMessageOn(String broadcastIPAddress, int port, String message) throws Exception {
		DatagramSocket socket = socket = new DatagramSocket();
		socket.setBroadcast(true);
		DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, InetAddress.getByName(broadcastIPAddress), port);
		socket.send(packet);
		socket.close();
	}

}
