package com.thomascantie.insa.model.network.util;

import java.net.*;
import java.util.List;

/**
 * Classe utilitaire permettant de récupérer l'adresse IP de broadcast
 *
 * @author Thomas Cantié
 * @author Andy Piszyna
 */
public class LocalNetworkBroadcastAddress {

	/**
	 * Accesseur
	 *
	 * @return l'adresse de boradcast du réseau local ou null en cas d'erreur système
	 * @throws SocketException      si une erreur survient lors de la création ou l'utilisation d'un socket
	 * @throws UnknownHostException si l'hôte ne peut être retrouvé
	 */
	public static String getBroadcastIPAddress() throws SocketException, UnknownHostException {
		List<InterfaceAddress> interfaces = NetworkInterface.getByInetAddress(InetAddress.getByName(LocalNetworkIPAddress.getLocalIPAddress())).getInterfaceAddresses();
		int i;
		for (i = 0; i < interfaces.size() && interfaces.get(i).getBroadcast() == null; i++) {
		}

		if (i < interfaces.size())
			return interfaces.get(i).getBroadcast().toString().split("/")[1];

		return null;
	}

}
