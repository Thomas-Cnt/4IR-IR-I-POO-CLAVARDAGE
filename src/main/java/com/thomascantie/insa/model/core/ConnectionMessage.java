package com.thomascantie.insa.model.core;

/**
 * Classe représentant des messages de connexion
 *
 * @author Thomas Cantié
 * @author Andy Piszyna
 * @see User
 * @see State
 */
public class ConnectionMessage {

	/**
	 * Utilisateur de la connexion
	 */
	private User user;
	/**
	 * Numéro de port associé à la connexion
	 */
	private int portNumber;
	/**
	 * Etat de la connexion
	 */
	private State state;

	/**
	 * Constructeur
	 *
	 * @param user  l'utilisateur associé à la connexion
	 * @param port  le numéro de port associé à la connexion
	 * @param state l'état de la connexion
	 */
	public ConnectionMessage(User user, int port, State state) {
		this.user = user;
		this.portNumber = port;
		this.state = state;
	}

	/**
	 * Constructeur
	 * Créé un message de connexion à partir d'une chaîne formatée
	 *
	 * @param data les données formatées
	 */
	public ConnectionMessage(String data) {
		int sepState = data.indexOf("--");
		int sepPort = data.indexOf(":");
		this.user = new User(data.substring(0, sepState));
		this.portNumber = Integer.parseInt(data.substring(sepPort + 1));
		this.state = State.valueOf(data.substring(sepState + 2, sepPort));
	}

	/**
	 * Accesseur en lecture
	 *
	 * @return le pseudo de l'utilisateur associé à la connexion
	 */
	public String getPseudo() {
		return this.user.getPseudo();
	}

	/**
	 * Evaluateur
	 *
	 * @return true si la connexion est ouverte ; false sinon
	 */
	public boolean isConnectionOn() {
		return this.state.equals(State.ON);
	}

	/**
	 * Accesseur en lecture
	 *
	 * @return le numéro de port associé à la connexion
	 */
	public int getPortNumber() {
		return this.portNumber;
	}

	@Override
	public String toString() {
		return this.user.getPseudo() + "--" + this.state + ":" + this.portNumber;
	}

}
