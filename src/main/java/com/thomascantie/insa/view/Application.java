package com.thomascantie.insa.view;

import com.thomascantie.insa.controler.ApplicationListener;
import com.thomascantie.insa.model.core.ChatManager;
import com.thomascantie.insa.model.core.ConnectionsManager;
import com.thomascantie.insa.model.core.User;
import com.thomascantie.insa.model.network.util.LocalNetworkIPAddress;
import com.thomascantie.insa.model.network.util.PortNumber;

import javax.swing.*;
import java.net.InetAddress;


public class Application extends JFrame {

	private static Application instance;
	private boolean setPseudo;

	private Application() {
		super("Application de clavardage");

		this.setContentPane(new InputPseudo());
	}

	public static synchronized Application getInstance() {
		if (instance == null)
			instance = new Application();
		return instance;
	}

	public void start() {
		this.setPseudo = false;
		this.pack();
		this.setResizable(false);
		this.setSize(400, 80);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(new ApplicationListener());
		this.setVisible(true);
	}

	public void switchContentPane(String pseudo) throws Exception {
		this.setPseudo = true;

		this.setTitle(this.getTitle() + " - " + pseudo);

		User usr = new User(pseudo);

		String localIPAddress = LocalNetworkIPAddress.getLocalIPAddress();

		int listeningPortNumber = PortNumber.generate();

		ViewConnections view = new ViewConnections(usr, localIPAddress, listeningPortNumber);

		ConnectionsManager.getInstance().setUser(usr);
		ConnectionsManager.getInstance().setViewConnections(view);
		ConnectionsManager.getInstance().listeningNotifications();
		ConnectionsManager.getInstance().notifyConnectionOn();

		ChatManager.getInstance().setPseudo(pseudo);
		ChatManager.getInstance().setLocalIPAddress(InetAddress.getByName(localIPAddress));
		ChatManager.getInstance().setLocalPortNumber(listeningPortNumber);
		ChatManager.getInstance().listeningIncommingMessages();

		this.setContentPane(view);
		this.validate();
		this.repaint();
		this.pack();
		this.setResizable(false);
		this.setSize(600, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public boolean isSetPseudo() {
		return this.setPseudo;
	}

}
