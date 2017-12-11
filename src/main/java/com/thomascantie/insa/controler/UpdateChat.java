package com.thomascantie.insa.controler;

import com.thomascantie.insa.model.core.Message;
import com.thomascantie.insa.model.network.service.IncomingMessageListener;
import com.thomascantie.insa.view.ViewChat;

import java.net.InetAddress;
import java.util.Date;

/**
 * Traitement des messages de chat entrants
 *
 * @author Thomas cantié
 * @author Andy Piszyna
 * @see IncomingMessageListener
 * @see ViewChat
 */
public class UpdateChat implements IncomingMessageListener {

	/**
	 * Constante de séparation des messages
	 */
	private static final String MESSAGE_SEPARATOR = "\n";
	/**
	 * Vue du chat courant
	 */
	private ViewChat chat;

	/**
	 * Constructeur
	 *
	 * @param viewChat le vue du chat courant
	 */
	public UpdateChat(ViewChat viewChat) {
		this.chat = viewChat;
	}

	/**
	 * Traitement des messages entrants
	 *
	 * @param ipAddress adresse IP du message
	 * @param msg       contenu du message
	 */
	@Override
	public void onNewIncomingMessage(InetAddress ipAddress, String msg) {
		Message message = new Message(msg);

		this.chat.updateTextPane(
				new Date(System.currentTimeMillis()),
				this.chat.getTo(),
				message.getContent() + MESSAGE_SEPARATOR);
	}

}
