package com.thomascantie.insa.controler;

import com.thomascantie.insa.model.core.ConnectionMessage;
import com.thomascantie.insa.model.core.ConnectionsManager;
import com.thomascantie.insa.model.core.State;
import com.thomascantie.insa.model.network.service.IncomingMessageListener;
import com.thomascantie.insa.model.network.service.UDPMessageSenderService;
import com.thomascantie.insa.model.network.util.LocalNetworkIPAddress;
import com.thomascantie.insa.view.ViewConnections;

import java.net.InetAddress;
import java.net.SocketException;

/**
 * Traitement des messages de connexion entrants
 *
 * @author Thomas Cantié
 * @author Andy Piszyna
 * @see IncomingMessageListener
 * @see ViewConnections
 */
public class UpdateConnection implements IncomingMessageListener {

	/**
	 * Le panneau de visualisation des connexions
	 */
	private ViewConnections view;

	/**
	 * Constructeur
	 *
	 * @param view la vue des connexions courantes
	 */
	public UpdateConnection(ViewConnections view) {
		this.view = view;
	}

	/**
	 * Interface de traitement des messages entrants
	 *
	 * @param ipAddress adresse IP du message
	 * @param msg       contenu du message
	 */
	@Override
	public void onNewIncomingMessage(InetAddress ipAddress, String msg) {

		try {
			if (!ipAddress.getHostAddress().equals(LocalNetworkIPAddress.getLocalIPAddress())) {

				msg = msg.trim();

				ConnectionMessage connect = new ConnectionMessage(msg);

				if (!ConnectionsManager.getInstance().contains(connect.getPseudo())) {
					try {
						new UDPMessageSenderService().sendMessageOn(ipAddress.getHostAddress(), 1234, new ConnectionMessage(this.view.getLocalUser(), this.view.getLocalPortNumber(), State.ON).toString());

						if (connect.isConnectionOn()) {
							this.view.addNewConnection(connect.getPseudo());
							ConnectionsManager.getInstance().updateConnexionInfo(connect.getPseudo(), ipAddress, connect.getPortNumber());
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (!connect.isConnectionOn()) {
					this.view.removeConnection(connect.getPseudo());
					ConnectionsManager.getInstance().removeConnectionInfo(connect.getPseudo());

				}
			}

		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

}
