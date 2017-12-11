package com.thomascantie.insa.controler;

import com.thomascantie.insa.view.Application;
import com.thomascantie.insa.view.InputPseudo;

public class ListenerPseudoValid {

	protected InputPseudo panelPseudo;

	public ListenerPseudoValid(InputPseudo panel) {
		this.panelPseudo = panel;
	}

	public void process() {
		try {
			Application.getInstance().switchContentPane(this.panelPseudo.getPseudo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
