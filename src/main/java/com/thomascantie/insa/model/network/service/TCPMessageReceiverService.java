package com.thomascantie.insa.model.network.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe de service reposant sur le protocole de transport UDP pour recevoir des messages
 *
 * @author Thomas cantié
 * @author Andy Piszyna
 */
public class TCPMessageReceiverService implements MessageReceiverService {

	/**
	 * Mise en écoute des messages entrants
	 *
	 * @param port Le port sur lequel écouter
	 * @throws Exception si une erreur système survient
	 */
	public void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception {
		ServerSocket server = new ServerSocket(port);
		new Thread(new WaitingMessages(server, incomingMessageListener)).start();
	}

}

/**
 * Thread de traitement des messages entrants
 *
 * @author Thomas cantié
 * @author Andy Piszyna
 * @see Runnable
 */
class WaitingMessages implements Runnable {

	/**
	 * Socket d'écoute
	 */
	private ServerSocket serverSocket;
	/**
	 * Traitement des messages entrants
	 */
	private IncomingMessageListener listener;

	/**
	 * Constructeur
	 *
	 * @param serverSocket la socket d'écoute
	 */
	public WaitingMessages(ServerSocket serverSocket, IncomingMessageListener listener) {
		this.serverSocket = serverSocket;
		this.listener = listener;
	}

	/**
	 * Traitement
	 */
	@Override
	public void run() {
		Socket socket;
		try {
			while (true) {
				socket = this.serverSocket.accept(); // block current thread until client asks for a connection
				InetAddress ipAddress = socket.getInetAddress();

				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				this.listener.onNewIncomingMessage(ipAddress, reader.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}