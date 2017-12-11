package com.thomascantie.insa.model.network.util;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Classe utilitaire permettant la "génération" d'un numéro de port valide
 */
public class PortNumber {

	/**
	 * Accesseur
	 *
	 * @return un numéro de port non utilisé par la machine ; -1 en cas d'erreur système
	 */
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
