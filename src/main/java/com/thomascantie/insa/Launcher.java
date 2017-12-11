package com.thomascantie.insa;

import com.thomascantie.insa.model.core.ChatManager;
import com.thomascantie.insa.model.core.ConnectionsManager;
import com.thomascantie.insa.model.core.User;
import com.thomascantie.insa.model.network.util.LocalNetworkIPAddress;
import com.thomascantie.insa.model.network.util.PortNumber;
import com.thomascantie.insa.view.ViewConnections;

import javax.swing.*;

import java.net.InetAddress;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Launcher {

	public static void main(String[] args) throws Exception {

		User usr = new User(args[0]);

		String localIPAddress = LocalNetworkIPAddress.getLocalIPAddress();
		int listeningPortNumber = PortNumber.generate();

		ViewConnections view = new ViewConnections(usr, localIPAddress, listeningPortNumber);

		ConnectionsManager.getInstance().setUser(usr);
		ConnectionsManager.getInstance().setViewConnections(view);
		ConnectionsManager.getInstance().listeningNotifications();
		ConnectionsManager.getInstance().notifyConnectionOn();

		ChatManager.getInstance().setPseudo(args[0]);
		ChatManager.getInstance().setLocalIPAddress(InetAddress.getByName(localIPAddress));
		ChatManager.getInstance().setLocalPortNumber(listeningPortNumber);
		ChatManager.getInstance().listeningIncommingMessages();

		JFrame frame = new JFrame("Application de clavardage");

		frame.setContentPane(view);

		frame.pack();
		frame.setResizable(false);
		frame.setSize(600, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);

		//Thread.sleep(3000);

		//ConnectionsManager.getInstance().notifyConnectionOff();

	}

}
