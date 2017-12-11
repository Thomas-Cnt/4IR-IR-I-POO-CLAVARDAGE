package com.thomascantie.insa.controler;

import com.thomascantie.insa.view.Application;
import com.thomascantie.insa.view.InputPseudo;

/**
 * Traitement de la validation du pseudo
 */
public class ListenerPseudoValid {

	/**
	 * Pseudo saisi
	 */
	protected InputPseudo panelPseudo;

	/**
	 * Constructeur
	 *
	 * @param panelPseudo le panneau de saisie du pseudo
	 */
	public ListenerPseudoValid(InputPseudo panelPseudo) {
		this.panelPseudo = panelPseudo;
	}

	/**
	 * Processus de changement de vue gérée par l'application
	 */
	public void process() {
		try {
			Application.getInstance().switchContentPane(this.panelPseudo.getPseudo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
