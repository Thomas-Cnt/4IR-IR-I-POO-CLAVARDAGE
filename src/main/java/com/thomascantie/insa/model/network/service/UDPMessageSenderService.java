package com.thomascantie.insa.model.network.service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPMessageSenderService implements MessageSenderService {

	@Override
	public void sendMessageOn(String ipAddress, int port, String message) throws Exception {
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(ipAddress), port);
		socket.send(packet);
		socket.close();
	}

}
