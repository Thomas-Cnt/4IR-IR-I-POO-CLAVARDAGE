package com.thomascantie.insa.model.network.service;

import java.net.InetAddress;

/**
 * Interface de traitement des messages entrants
 */
public interface IncomingMessageListener {

	/**
	 * Traitement des messages entrants
	 *
	 * @param ipAddress  adresse IP du message
	 * @param msg        contenu du message
	 */
	public void onNewIncomingMessage(InetAddress ipAddress, String msg);

}
