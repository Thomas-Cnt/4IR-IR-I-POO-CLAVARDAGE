package com.thomascantie.insa.model.network.service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPBroadcastSenderService implements MessageSenderService {

	@Override
	public void sendMessageOn(String broadcastIPAddress, int port, String message) throws Exception {
		DatagramSocket socket = socket = new DatagramSocket();
		socket.setBroadcast(true);
		DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, InetAddress.getByName(broadcastIPAddress), port);
		socket.send(packet);
		socket.close();
	}

}
