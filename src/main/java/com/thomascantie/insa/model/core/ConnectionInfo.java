package com.thomascantie.insa.model.core;

/**
 * Classe d'encapsulation des informations de connexion
 *
 * @author Thomas Cantié
 * @author Andy Piszyna
 */
public class ConnectionInfo {

	/**
	 * Adresse IP
	 */
	private String ipAddress;
	/**
	 * Numéro de port
	 */
	private int portNumber;

	/**
	 * Constructeur
	 *
	 * @param ipAddress  adresse IP
	 * @param portNumber numéro de port
	 */
	public ConnectionInfo(String ipAddress, int portNumber) {
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
	}

	/**
	 * Accesseur en lecture
	 *
	 * @return l'adresse IP
	 */
	public String getIPAddress() {
		return this.ipAddress;
	}

	/**
	 * Accesseur en lecture
	 *
	 * @return le numéro de port
	 */
	public int getPortNumber() {
		return this.portNumber;
	}

}
