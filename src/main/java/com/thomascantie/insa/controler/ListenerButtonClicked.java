package com.thomascantie.insa.controler;

import com.thomascantie.insa.view.ViewChat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerButtonClicked extends ListenerSendMessage implements ActionListener {

	public ListenerButtonClicked(ViewChat chat) {
		super(chat);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!this.chat.getTextInput().trim().isEmpty()) {
			super.process();
		}
	}

}
