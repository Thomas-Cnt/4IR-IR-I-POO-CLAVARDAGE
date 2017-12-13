package com.thomascantie.insa.network;

import com.thomascantie.insa.model.network.service.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class NetworkServicesMust {

	private static final int PORT = 4242;
	private static final int PORT_TCP_UNICAST = PORT + 1;
	private static final int PORT_UDP_UNICAST = PORT + 2;
	private static final int PORT_UDP_BROADCAST = PORT + 3;
	private static final String IP_ADDRESS = "127.0.0.1";
	private static final String MESSAGE_OVERWATCH = "The world needs heroes !";

	@Test
	public void send_and_receive_an_unicast_message_through_the_network_using_tcp_protocol() throws Exception {
		new TCPMessageReceiverService().listenOnPort(PORT_TCP_UNICAST, (ipAddress, msg) -> assertThat(msg, containsString(MESSAGE_OVERWATCH)));

		new TCPMessageSenderService().sendMessageOn(IP_ADDRESS, PORT_TCP_UNICAST, MESSAGE_OVERWATCH);
	}

	@Test
	public void send_and_receive_an_unicast_message_through_the_network_using_udp_protocol() throws Exception {
		new UDPMessageReceiverService().listenOnPort(PORT_UDP_UNICAST, (ipAddress, msg) -> assertThat(msg, containsString(MESSAGE_OVERWATCH)));

		new UDPMessageSenderService().sendMessageOn(IP_ADDRESS, PORT_UDP_UNICAST, MESSAGE_OVERWATCH);
	}

	@Test
	public void send_and_receive_a_broadcast_message_through_the_network_using_udp_protocol() throws Exception {
		new UDPMessageReceiverService().listenOnPort(PORT_UDP_BROADCAST, (ipAddress, msg) -> assertThat(msg, containsString(MESSAGE_OVERWATCH)));

		new UDPBroadcastSenderService().sendMessageOn(IP_ADDRESS, PORT_UDP_BROADCAST, MESSAGE_OVERWATCH);
	}

}
