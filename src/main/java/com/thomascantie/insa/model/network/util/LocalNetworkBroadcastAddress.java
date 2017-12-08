package com.thomascantie.insa.model.network.util;

import java.net.*;
import java.util.List;

public class LocalNetworkBroadcastAddress {

	public static String getBroadcastIPAddress() throws SocketException, UnknownHostException {
		List<InterfaceAddress> interfaces = NetworkInterface.getByInetAddress(InetAddress.getByName(LocalNetworkIPAddress.getLocalIPAddress())).getInterfaceAddresses();
		int i;
		for(i = 0; i < interfaces.size() && interfaces.get(i).getBroadcast() == null; i++) { }

		if (i < interfaces.size())
			return interfaces.get(i).getBroadcast().toString().split("/")[1];

		return null;
	}

}
