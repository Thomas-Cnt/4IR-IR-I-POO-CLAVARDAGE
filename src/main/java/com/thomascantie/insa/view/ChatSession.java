package com.thomascantie.insa.view;

import javax.swing.*;

/**
 * Classe représentant une session de chat unicast
 *
 * @author Thomas Cantié
 * @author Andy Piszyna
 * @see JFrame
 * @see ViewChat
 */
public class ChatSession extends JFrame {

	/**
	 * Vue du chat courant
	 */
	private ViewChat view;

	/**
	 * Constructeur
	 *
	 * @param localPseudo  le pseudo de l'utilisateur local
	 * @param remotePseudo le pseudo de l'utilisateur distant
	 * @param ip           l'adresse IP distante
	 * @param port         le port d'écoute distant
	 */
	public ChatSession(String localPseudo, String remotePseudo, String ip, int port) {
		super("Session de chat avec " + remotePseudo + " (" + ip + ":" + port + ")");

		this.view = new ViewChat(localPseudo, remotePseudo, ip, port);

		this.setContentPane(this.view);
		this.pack();
		this.setResizable(true);
		this.setSize(1000, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	/**
	 * Accesseur en lecture
	 *
	 * @return la vue du chat courant
	 */
	public ViewChat getView() {
		return this.view;
	}
}
