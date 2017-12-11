package com.thomascantie.insa.controler;

import com.thomascantie.insa.model.core.ConnectionsManager;
import com.thomascantie.insa.view.Application;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Traitement de changement de vue de l'application
 *
 * @author Thomas Cantié
 * @author Andy Piszyna
 * @see WindowListener
 */
public class ApplicationListener implements WindowListener {

	@Override
	public void windowOpened(WindowEvent windowEvent) {

	}

	@Override
	public void windowClosing(WindowEvent windowEvent) {
		if (Application.getInstance().isSetPseudo()) {
			try {
				System.out.println("--- Closing application ---");
				ConnectionsManager.getInstance().notifyConnectionOff();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void windowClosed(WindowEvent windowEvent) {

	}

	@Override
	public void windowIconified(WindowEvent windowEvent) {

	}

	@Override
	public void windowDeiconified(WindowEvent windowEvent) {

	}

	@Override
	public void windowActivated(WindowEvent windowEvent) {

	}

	@Override
	public void windowDeactivated(WindowEvent windowEvent) {

	}

}
