package com.thomascantie.insa.model.network.service;

import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Classe de service reposant sur le protocole de transport TCP pour envoyer un message unicast
 *
 * @author Thomas cantié
 * @author Andy Piszyna
 * @see MessageSenderService
 */
public class TCPMessageSenderService implements MessageSenderService {

	/**
	 * Envoie un message via le réseau
	 *
	 * @param ipAddress L'adresse IP à laquelle envoyer le message
	 * @param port      Le numéro de port sur lequel envoyer le message
	 * @param message   Le message à envoyer via le réseau
	 * @throws Exception si une erreur système survient
	 */
	@Override
	public void sendMessageOn(String ipAddress, int port, String message) throws Exception {
		Socket socket = new Socket();
		socket.connect(new InetSocketAddress(ipAddress, port)); // asks for a connection
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		out.println(message);
		out.flush();
		socket.close();
	}

}
