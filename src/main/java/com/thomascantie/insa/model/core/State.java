package com.thomascantie.insa.model.core;

/**
 * Enumération représentant l'état d'une connexion
 */
public enum State {

	ON("ON"), OFF("OFF");

	/**
	 * Statut d ela connexion
	 */
	private String status;

	/**
	 * Constructeur
	 * @param status statut de la connexion
	 */
	State(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return this.status;
	}

}
