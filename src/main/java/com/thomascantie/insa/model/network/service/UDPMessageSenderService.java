package com.thomascantie.insa.model.network.service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Classe de service reposant sur le protocole de transport UDP pour envoyer un message unicast
 *
 * @author Thomas cantié
 * @author Andy Piszyna
 * @see MessageSenderService
 */
public class UDPMessageSenderService implements MessageSenderService {

	/**
	 * Envoie un message UDP via le réseau
	 *
	 * @param ipAddress L'adresse IP à laquelle envoyer le message
	 * @param port      Le numéro de port sur lequel envoyer le message
	 * @param message   Le message à envoyer via le réseau
	 * @throws Exception si une erreur système survient
	 */
	@Override
	public void sendMessageOn(String ipAddress, int port, String message) throws Exception {
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(ipAddress), port);
		socket.send(packet);
		socket.close();
	}

}
