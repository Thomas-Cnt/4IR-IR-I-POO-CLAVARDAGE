package com.thomascantie.insa.view;

import com.thomascantie.insa.model.core.ChatManager;
import com.thomascantie.insa.model.core.ConnectionsManager;
import com.thomascantie.insa.model.core.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant la vue des connexions courantes
 *
 * @author Thomas Cantié
 * @author Andy Piszyna
 * @see JPanel
 */
public class ViewConnections extends JPanel {

	/**
	 * Utilisateur local
	 */
	private User user;
	/**
	 * Adresse IP locale
	 */
	private String localIPAddress;
	/**
	 * Numéro du port d'écoute
	 */
	private int listeningPort;
	/**
	 * Sélection parmis les connexions courantes
	 */
	private List<JRadioButton> choices;
	/**
	 * Panneau d'affichage des connexions courantes
	 */
	private JPanel panel;
	/**
	 * Regroupenet des différents choix possibles
	 */
	private ButtonGroup group;

	/**
	 * Constructeur
	 *
	 * @param user       l'utilisateur local
	 * @param ipAddress  l'adresse IP locale
	 * @param portNumber le numéro du port d'écoute
	 */
	public ViewConnections(User user, String ipAddress, int portNumber) {
		super(new BorderLayout());

		this.user = user;
		this.localIPAddress = ipAddress;
		this.listeningPort = portNumber;

		this.choices = new ArrayList<JRadioButton>();

		JLabel label = new JLabel("Liste des personnes actuellement connectées", JLabel.CENTER);

		this.add(label, BorderLayout.NORTH);

		this.panel = new JPanel(new GridBagLayout());
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

		this.group = new ButtonGroup();

		for (String pseudo : ConnectionsManager.getInstance().getAllPseudos()) {
			JRadioButton radio = new JRadioButton(pseudo);
			this.choices.add(radio);
			this.panel.add(radio);
			this.group.add(radio);
		}

		this.add(panel, BorderLayout.CENTER);

		JButton btn = new JButton("Démarrer une session de clavardage");
		btn.addActionListener(e -> exam());

		this.add(btn, BorderLayout.SOUTH);

	}

	/**
	 * Modificateur
	 * Ajoute une nouvelle connexion
	 *
	 * @param pseudo le pseudo associé à la nouvelle connexion
	 */
	public void addNewConnection(String pseudo) {
		JRadioButton radio = new JRadioButton(pseudo);
		this.choices.add(radio);
		this.group.add(radio);
		this.panel.add(radio);
		this.panel.validate();
		this.panel.repaint();
	}

	/**
	 * Modificateur
	 * Supprime une connexion
	 *
	 * @param pseudo le pseudo associé à la connexion
	 */
	public void removeConnection(String pseudo) {
		int i;
		for (i = 0; i < this.choices.size() && !this.choices.get(i).getText().equals(pseudo); i++) {
		}
		if (i < this.choices.size()) {
			JRadioButton radio = this.choices.remove(i);
			this.group.remove(radio);
			this.panel.remove(radio);
			this.panel.validate();
			this.panel.repaint();
		}
	}

	/**
	 * Evaluateur
	 * Examine la connexion sélectionnée et lance une session de chat ou continue une session existante, le cas échéant
	 */
	private void exam() {

		int i;
		for (i = 0; i < this.choices.size() && !this.choices.get(i).isSelected(); i++) {
		}

		if (i < this.choices.size()) {
			JRadioButton radio = this.choices.get(i);
			String pseudo = radio.getText();
			radio.setEnabled(false);
			radio.setText(pseudo);

			System.out.println("Session de chat avec " + pseudo);

			String addr = ConnectionsManager.getInstance().getConnexionInfoFor(pseudo).getIPAddress();

			if (!ChatManager.getInstance().hasChatWith(addr)) {
				ChatManager.getInstance().addNewChat(pseudo, addr, ConnectionsManager.getInstance().getConnexionInfoFor(pseudo).getPortNumber());
			}

			ChatManager.getInstance().getChat(addr).setVisible(true);

		} else
			System.out.println("Aucune personne sélectionée !");


	}

	/**
	 * Accesseur
	 *
	 * @return l'utilisateur local
	 */
	public User getLocalUser() {
		return this.user;
	}

	/**
	 * Accesseur
	 *
	 * @return le numéro de port local
	 */
	public int getLocalPortNumber() {
		return this.listeningPort;
	}

}