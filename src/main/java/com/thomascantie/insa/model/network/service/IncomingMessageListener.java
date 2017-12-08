package com.thomascantie.insa.model.network.service;

import java.net.InetAddress;

public interface IncomingMessageListener {

	public void onNewIncomingMessage(InetAddress ipAddress, int portNumber, String msg);

}
