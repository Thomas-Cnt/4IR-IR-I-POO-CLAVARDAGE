package com.thomascantie.insa.controler;

import com.thomascantie.insa.model.core.Message;
import com.thomascantie.insa.model.network.service.TCPMessageSenderService;
import com.thomascantie.insa.view.ViewChat;

import java.util.Date;

public class ListenerSendMessage {

	private static final String MESSAGE_SEPARATOR = "\n";

	protected ViewChat chat;

	public ListenerSendMessage(ViewChat chat) {
		this.chat = chat;
	}

	public void process() {
		try {
			new TCPMessageSenderService().sendMessageOn(this.chat.getIpRemote(), this.chat.getRemotePort(), new Message(new Date(System.currentTimeMillis()), this.chat.getTo(), this.chat.getTextInput()).formatToSend());
			this.chat.updateTextPane(new Date(System.currentTimeMillis()), this.chat.getFrom(), this.chat.getTextInput() + MESSAGE_SEPARATOR);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
