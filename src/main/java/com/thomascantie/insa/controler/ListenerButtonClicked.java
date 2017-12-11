package com.thomascantie.insa.controler;

import com.thomascantie.insa.view.ViewChat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ecouteur de validation des messages
 *
 * @author Thomas Cantié
 * @author Andy Piszyna
 * @see ListenerSendMessage
 * @see ActionListener
 */
public class ListenerButtonClicked extends ListenerSendMessage implements ActionListener {

	/**
	 * Constructeur
	 *
	 * @param chat la visualisation des messages du chat
	 */
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
