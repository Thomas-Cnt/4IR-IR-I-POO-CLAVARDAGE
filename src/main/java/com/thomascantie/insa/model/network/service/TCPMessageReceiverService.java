package com.thomascantie.insa.model.network.service;

import com.thomascantie.insa.controler.UpdateChat;
import com.thomascantie.insa.model.core.ChatManager;
import com.thomascantie.insa.model.core.ConnectionsManager;
import com.thomascantie.insa.view.ChatSession;
import com.thomascantie.insa.view.ViewChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPMessageReceiverService {

	public void listenOnPort(int port) throws Exception {
		ServerSocket server = new ServerSocket(port);
		new Thread(new WaitingMessages(server)).start();
	}

}

class WaitingMessages implements Runnable {

	private ServerSocket serverSocket;

	public WaitingMessages(ServerSocket serverSocket){
		this.serverSocket = serverSocket;
	}

	@Override
	public void run() {
		Socket socket;
		try {
			while(true){
				socket = this.serverSocket.accept(); // block current thread until client asks for a connection
				InetAddress ipAddress = socket.getInetAddress();
				int portNumber = socket.getPort();
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				if (!ChatManager.getInstance().hasChatWith(ipAddress.getHostAddress())) {
				    ChatManager.getInstance().addNewChat(ConnectionsManager.getInstance().getPseudoAtIP(ipAddress), ipAddress.getHostAddress(), ConnectionsManager.getInstance().getPortAtIP(ipAddress));
                }

				ChatSession session = ChatManager.getInstance().getChat(ipAddress.getHostAddress());

                new UpdateChat(session.getView()).onNewIncomingMessage(ipAddress, portNumber, reader.readLine());

				session.setVisible(true);


			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}