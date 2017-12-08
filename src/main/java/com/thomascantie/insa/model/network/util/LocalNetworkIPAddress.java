package com.thomascantie.insa.model.network.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LocalNetworkIPAddress {

	public static String getLocalIPAddress() throws SocketException {
		InetAddress addr = null;

		Iterator<NetworkInterface> it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
		boolean found = false;

		while (it.hasNext() && !found) {
			NetworkInterface net = it.next();
			if(net.isLoopback()) continue;

			List<InetAddress> addresses = Collections.list(net.getInetAddresses());

			for (int i = 0; i < addresses.size() && !found; i++) {
				if (addresses.get(i).isSiteLocalAddress()) {
					found = true;
					addr = addresses.get(i);
				}
			}

		}

		return addr.getHostAddress();
	}

}
