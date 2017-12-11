package com.thomascantie.insa.controler;

import com.thomascantie.insa.model.core.Message;
import com.thomascantie.insa.model.network.service.IncomingMessageListener;
import com.thomascantie.insa.view.ViewChat;

import java.net.InetAddress;
import java.util.Date;

public class UpdateChat implements IncomingMessageListener {

	private static final String MESSAGE_SEPARATOR = "\n";
	private ViewChat chat;

	public UpdateChat(ViewChat viewChat) {
		this.chat = viewChat;
	}

	@Override
	public void onNewIncomingMessage(InetAddress ipAddress, int portNumber, String msg) {

		Message message = new Message(msg);

		String pseudo = message.getUserTo().getPseudo();

		message.getDate();
		message.getContent();

		this.chat.updateTextPane(
				new Date(System.currentTimeMillis()),
				this.chat.getTo(),
				message.getContent() + MESSAGE_SEPARATOR);
	}

}
