package com.thomascantie.insa.model.network.service;

/**
 * Interface de service reposant sur le protocole de transport UDP pour recevoir des messages
 *
 * @see IncomingMessageListener
 */
public interface MessageReceiverService {

	/**
	 * Mise en écoute des messages entrants
	 *
	 * @param port                    Le port sur lequel écouter
	 * @param incomingMessageListener Le déclencheur des nouveaux messages
	 * @throws Exception si une erreur système survient
	 */
	void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception;

}
