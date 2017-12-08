package com.thomascantie.insa.view;

import com.thomascantie.insa.controler.UpdateChat;
import com.thomascantie.insa.model.network.service.TCPMessageReceiverService;

import javax.swing.*;

public class ChatSession extends JFrame {

	private String remotePseudo;
	private ViewChat view;

	public ChatSession(String s1, String s2, String ipLocal, String ip, int portLocal, int port) {
		super("Session de chat entre " + s1 + " et " + s2 + "(" + ip + ":" + port + ")");

		this.remotePseudo = s2;

		this.view = new ViewChat(s1, s2, ipLocal, ip, portLocal, port);

		this.setContentPane(this.view);

		try {
			new TCPMessageReceiverService().listenOnPort(portLocal, new UpdateChat(view));
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.pack();
		this.setResizable(true);
		this.setSize(1000, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//this.addWindowListener(new ClosingFrameListener(casimir));

	}

	public String getRemotePseudo() {
		return this.remotePseudo;
	}

	public ViewChat getView() {
		return this.view;
	}
}
