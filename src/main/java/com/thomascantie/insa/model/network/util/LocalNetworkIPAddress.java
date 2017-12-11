package com.thomascantie.insa.model.network.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Classe utilitaire permettant de récuperer l'adresse IP locale
 *
 * @author Thomas Cantié
 * @author Andy Piszyna
 */
public class LocalNetworkIPAddress {

	/**
	 * Accesseur
	 *
	 * @return l'adresse IP de la machine sur le réseau local ou null en cas d'erreur système
	 * @throws SocketException si une erreur survient lors de la création ou l'utilisation d'un socket
	 */
	public static String getLocalIPAddress() throws SocketException {
		InetAddress addr = null;

		Iterator<NetworkInterface> it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
		boolean found = false;

		while (it.hasNext() && !found) {
			NetworkInterface net = it.next();
			if (net.isLoopback()) continue;

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
