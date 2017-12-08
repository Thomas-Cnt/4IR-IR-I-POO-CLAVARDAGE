package com.thomascantie.insa.view;

import com.thomascantie.insa.controler.ListenerButtonClicked;
import com.thomascantie.insa.controler.ListenerEnterPressed;
import com.thomascantie.insa.controler.UpdateChat;
import com.thomascantie.insa.model.core.User;
import com.thomascantie.insa.model.network.service.TCPMessageReceiverService;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class ViewChat extends JPanel {

	private static final User BOT_INFO = new User("[info]");

	private JTextField textField;
	private ViewMessages textPane;

	private User from, to;
	private String ipLocal;
	private String ipRemote;
	private int listeningPort;
	private int remotePort;

	public User getFrom() {
		return from;
	}

	public User getTo() {
		return to;
	}

	public String getIpLocal() {
		return ipLocal;
	}

	public String getIpRemote() {
		return ipRemote;
	}

	public int getListeningPort() {
		return listeningPort;
	}

	public int getRemotePort() {
		return remotePort;
	}

	public ViewChat(String pseudo1, String pseudo2, String iplocal, String ipremote, int portLocal, int portRemote) {
		super();

		this.from = new User(pseudo1);
		this.to = new User(pseudo2);

		this.listeningPort = portLocal;
		this.remotePort = portRemote;

		this.ipLocal = iplocal;
		this.ipRemote = ipremote;

		this.setLayout(new BorderLayout());

		textPane = new ViewMessages();

		JPanel panelInput = new JPanel(new SpringLayout());

		JLabel label = new JLabel("Votre message :", JLabel.TRAILING);

		textField = new JTextField(20);
		textField.addKeyListener(new ListenerEnterPressed(this));

		JButton send = new JButton("Envoyer");
		send.addActionListener(new ListenerButtonClicked(this));

		panelInput.add(label);
		panelInput.add(Box.createHorizontalGlue());
		panelInput.add(textField);
		panelInput.add(Box.createHorizontalGlue());
		panelInput.add(send);

		SpringUtilities.makeCompactGrid(
				panelInput,      // component
				1, 5,       // rows, cols
				6, 6,       // initX, initY
				6, 6);      // xPad, yPad

		this.add(new JScrollPane(textPane), BorderLayout.CENTER);

		this.add(panelInput, BorderLayout.SOUTH);


		this.textField.requestFocus();

	}


	public String getTextInput() {
		return this.textField.getText();
	}

	public void updateTextPane(Date date, User user, String txt) {
		this.textPane.writeMessage(date, user, txt);
		this.textField.setText("");
	}


}
