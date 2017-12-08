package com.thomascantie.insa.controler;

import com.thomascantie.insa.view.ViewChat;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ListenerEnterPressed extends ListenerSendMessage implements KeyListener {

	public ListenerEnterPressed(ViewChat chat) {
		super(chat);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER && !this.chat.getTextInput().trim().isEmpty()) {
			super.process();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
