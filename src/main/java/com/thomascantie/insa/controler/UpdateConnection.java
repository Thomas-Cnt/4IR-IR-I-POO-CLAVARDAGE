package com.thomascantie.insa.controler;

import com.thomascantie.insa.model.core.ConnectionMessage;
import com.thomascantie.insa.model.core.ConnectionsManager;
import com.thomascantie.insa.model.core.State;
import com.thomascantie.insa.model.network.service.IncomingMessageListener;
import com.thomascantie.insa.model.network.service.UDPMessageSenderService;
import com.thomascantie.insa.view.ViewConnections;

import java.net.InetAddress;
import java.util.Arrays;

public class UpdateConnection implements IncomingMessageListener {

	private ViewConnections view;

	public UpdateConnection(ViewConnections view) {
		this.view = view;
	}

	@Override
	public void onNewIncomingMessage(InetAddress ipAddress, int portNumber, String msg) {
		msg = msg.trim();

		ConnectionMessage connect = new ConnectionMessage(msg);

		if (!ConnectionsManager.getInstance().contains(connect.getPseudo())) {
			try {
				new UDPMessageSenderService().sendMessageOn(ipAddress.getHostAddress(), 1234, new ConnectionMessage(this.view.getLocalUser(), this.view.getLocalPortNumber(), State.ON).toString());

				if (connect.isConnectionOn()) {
					this.view.addNewConnection(connect.getPseudo());
					ConnectionsManager.getInstance().updateConnexionInfo(connect.getPseudo(), ipAddress, connect.getPortNumber());
				} else {
					this.view.removeConnection(connect.getPseudo());
					ConnectionsManager.getInstance().removeConnectionInfo(connect.getPseudo());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
