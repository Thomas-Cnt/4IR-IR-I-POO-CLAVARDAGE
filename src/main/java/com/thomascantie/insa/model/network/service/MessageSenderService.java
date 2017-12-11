package com.thomascantie.insa.model.network.service;

/**
 * Interface de service d'envoi d'un message via le réseau
 */
public interface MessageSenderService {

	/**
	 * Envoie un message via le réseau
	 *
	 * @param ipAddress L'adresse IP à laquelle envoyer le message
	 * @param port      Le numéro de port sur lequel envoyer le message
	 * @param message   Le message à envoyer via le réseau
	 * @throws Exception si une erreur système survient
	 */
	void sendMessageOn(String ipAddress, int port, String message) throws Exception;

}
