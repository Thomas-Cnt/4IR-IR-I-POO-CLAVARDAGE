package com.thomascantie.insa.controler;

import com.thomascantie.insa.view.InputPseudo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ListenerEnteredPseudo extends ListenerPseudoValid implements KeyListener {

	public ListenerEnteredPseudo(InputPseudo panelPseudo) {
		super(panelPseudo);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER && !this.panelPseudo.getPseudo().isEmpty()) {
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
