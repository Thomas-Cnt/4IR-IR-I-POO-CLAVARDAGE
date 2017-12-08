package com.thomascantie.insa.model.network.util;

import java.io.IOException;
import java.net.ServerSocket;

public class PortNumber {

	public static int generate() {
		int port = -1;
		ServerSocket serveurTest = null;

		try {
			serveurTest = new ServerSocket(0);
			port = serveurTest.getLocalPort();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			serveurTest.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return port;
	}

}
