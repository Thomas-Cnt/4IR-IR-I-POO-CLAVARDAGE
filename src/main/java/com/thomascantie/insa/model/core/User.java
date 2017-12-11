package com.thomascantie.insa.model.core;

/**
 * Classe d'encapsulation des données d'un utilsiateur
 *
 * @author Thomas Cantié
 * @author Andy Piszyna
 */
public class User {

	/**
	 * Pseudo de l'utilisateur
	 */
	private String pseudo;

	/**
	 * Constructeur
	 *
	 * @param pseudo le pseudo de l'utilisateur
	 */
	public User(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * Accesseur en lecture
	 *
	 * @return le pseudo de l'utilisateur
	 */
	public String getPseudo() {
		return this.pseudo;
	}

	@Override
	public String toString() {
		return this.pseudo;
	}

}
