package com.thomascantie.insa.view;

import com.thomascantie.insa.model.core.ChatManager;
import com.thomascantie.insa.model.core.ConnectionsManager;
import com.thomascantie.insa.model.core.User;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ViewConnections extends JPanel {

	private User user;
	private String localIPAddress;
	private int listeningPort;
	private List<JRadioButton> choices;
	private JPanel panel;
	private ButtonGroup group;

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

	public void addNewConnection(String pseudo) {
		JRadioButton radio = new JRadioButton(pseudo);
		this.choices.add(radio);
		this.group.add(radio);
		this.panel.add(radio);
		this.panel.validate();
		this.panel.repaint();
	}

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

	private void exam() {

		int i;
		for (i = 0; i < this.choices.size() && !this.choices.get(i).isSelected(); i++) {
		}

		if (i < this.choices.size()) {
			JRadioButton radio = this.choices.get(i);
			String pseudo = radio.getText();
			radio.setEnabled(false);
			radio.setText(pseudo + " (sessison already opened)");
			radio.setSelected(false);

			System.out.println("Session de chat avec " + pseudo);

			String addr = ConnectionsManager.getInstance().getConnexionInfoFor(pseudo).getIPAddress();

			if (!ChatManager.getInstance().hasChatWith(addr)) {
				ChatManager.getInstance().addNewChat(pseudo, addr, ConnectionsManager.getInstance().getConnexionInfoFor(pseudo).getPortNumber());
			}

			ChatManager.getInstance().getChat(addr).setVisible(true);

		} else
			System.out.println("Aucune personne sélectionée !");


	}

	public User getLocalUser() {
		return this.user;
	}

	public String getLocalIPAddress() {
		return this.localIPAddress;
	}

	public int getLocalPortNumber() {
		return this.listeningPort;
	}

}