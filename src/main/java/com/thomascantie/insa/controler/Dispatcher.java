package com.thomascantie.insa.controler;

import com.thomascantie.insa.model.core.ChatManager;
import com.thomascantie.insa.model.core.ConnectionsManager;
import com.thomascantie.insa.model.core.Message;
import com.thomascantie.insa.model.network.service.IncomingMessageListener;
import com.thomascantie.insa.view.ChatSession;

import java.net.InetAddress;
import java.util.Date;

public class Dispatcher implements IncomingMessageListener {

	@Override
	public void onNewIncomingMessage(InetAddress ipAddress, String msg) {

		if (!ChatManager.getInstance().hasChatWith(ipAddress.getHostAddress())) {
			ChatManager.getInstance().addNewChat(ConnectionsManager.getInstance().getPseudoAtIP(ipAddress), ipAddress.getHostAddress(), ConnectionsManager.getInstance().getPortAtIP(ipAddress));
		}

		ChatSession session = ChatManager.getInstance().getChat(ipAddress.getHostAddress());

		Message message = new Message(msg);

		session.getView().updateTextPane(
				new Date(System.currentTimeMillis()),
				session.getView().getTo(),
				message.getContent() + "\n");

		session.setVisible(true);

	}

}
