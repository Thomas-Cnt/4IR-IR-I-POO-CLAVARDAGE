package com.thomascantie.insa.model.network.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPMessageReceiverService implements MessageReceiverService {

	@Override
	public void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception {
		ServerSocket server = new ServerSocket(port);
		new Thread(new WaitingMessages(server, incomingMessageListener)).start();
	}

}

class WaitingMessages implements Runnable {

	private ServerSocket serverSocket;
	private IncomingMessageListener incomingMessageListener;

	public WaitingMessages(ServerSocket serverSocket, IncomingMessageListener listener){
		this.serverSocket = serverSocket;
		this.incomingMessageListener = listener;
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
				this.incomingMessageListener.onNewIncomingMessage(ipAddress, portNumber, reader.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}