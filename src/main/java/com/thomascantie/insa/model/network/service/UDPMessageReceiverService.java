package com.thomascantie.insa.model.network.service;

import com.thomascantie.insa.model.network.util.LocalNetworkIPAddress;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPMessageReceiverService implements MessageReceiverService {

	@Override
	public void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception {
		DatagramSocket socket = new DatagramSocket(port);
		new Thread(new WaitingConnections(socket, incomingMessageListener)).start();
	}

}

class WaitingConnections implements Runnable {

	private DatagramSocket socket;
	private IncomingMessageListener listener;

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
				int portNumber = packet.getPort();

				System.out.println(">>> recv : " + new String(buffer));

				System.out.println("from " + ipAddress.getHostAddress() + ":" + portNumber);

				if (!ipAddress.getHostAddress().equals(LocalNetworkIPAddress.getLocalIPAddress())) {
					this.listener.onNewIncomingMessage(ipAddress, portNumber, new String(buffer));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}