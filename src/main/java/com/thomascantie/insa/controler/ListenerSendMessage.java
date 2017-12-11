package com.thomascantie.insa.controler;

import com.thomascantie.insa.model.core.Message;
import com.thomascantie.insa.model.network.service.TCPMessageSenderService;
import com.thomascantie.insa.view.ViewChat;

import java.util.Date;

/**
 * Classe de traitement d'envoi des messages
 *
 * @author Thomas Cantié
 * @author Andy Piszyna
 * @see ViewChat
 */
public class ListenerSendMessage {

	/**
	 * Constantce de séparation des messages
	 */
	private static final String MESSAGE_SEPARATOR = "\n";

	/**
	 * Panneau de visualisation des messages
	 */
	protected ViewChat chat;

	/**
	 * Constructeur
	 *
	 * @param chat la visualisation des messages du chat
	 */
	public ListenerSendMessage(ViewChat chat) {
		this.chat = chat;
	}

	/**
	 * Processus d'envoi du message via le réseau
	 *
	 * @see TCPMessageSenderService
	 */
	public void process() {
		try {
			System.out.println("*** send on : " + this.chat.getRemotePort());
			new TCPMessageSenderService().sendMessageOn(this.chat.getIpRemote(), this.chat.getRemotePort(), new Message(new Date(System.currentTimeMillis()), this.chat.getFrom(), this.chat.getTextInput()).formatToSend());

			this.chat.updateTextPane(new Date(System.currentTimeMillis()), this.chat.getFrom(), this.chat.getTextInput() + MESSAGE_SEPARATOR);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
