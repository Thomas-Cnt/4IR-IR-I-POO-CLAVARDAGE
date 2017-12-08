package com.thomascantie.insa.model.network.service;

public interface MessageSenderService {

	/**
	 * Send a message over the network to an IP address and a port number
	 *
	 * @param ipAddress The ip address on which send the message
	 * @param port      The port on which send the message
	 * @param message   The message to send through the network
	 */
	void sendMessageOn(String ipAddress, int port, String message) throws Exception;

}
