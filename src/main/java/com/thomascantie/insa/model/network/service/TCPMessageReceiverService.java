package com.thomascantie.insa.model.network.service;

import com.thomascantie.insa.controler.UpdateChat;
import com.thomascantie.insa.model.core.ChatManager;
import com.thomascantie.insa.model.core.ConnectionsManager;
import com.thomascantie.insa.view.ChatSession;

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
public class TCPMessageReceiverService {

	/**
	 * Mise en écoute des messages entrants
	 *
	 * @param port                    Le port sur lequel écouter
	 * @throws Exception si une erreur système survient
	 */
	public void listenOnPort(int port) throws Exception {
		ServerSocket server = new ServerSocket(port);
		new Thread(new WaitingMessages(server)).start();
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
	 * Constructeur
	 *
	 * @param serverSocket la socket d'écoute
	 */
	public WaitingMessages(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
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

				if (!ChatManager.getInstance().hasChatWith(ipAddress.getHostAddress())) {
					ChatManager.getInstance().addNewChat(ConnectionsManager.getInstance().getPseudoAtIP(ipAddress), ipAddress.getHostAddress(), ConnectionsManager.getInstance().getPortAtIP(ipAddress));
				}

				ChatSession session = ChatManager.getInstance().getChat(ipAddress.getHostAddress());

				new UpdateChat(session.getView()).onNewIncomingMessage(ipAddress, reader.readLine());

				session.setVisible(true);


			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}