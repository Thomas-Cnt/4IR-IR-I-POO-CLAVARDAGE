package com.thomascantie.insa.model.core;

public class User {

	private String pseudo;

	public User(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPseudo() {
		return this.pseudo;
	}

	@Override
	public String toString() {
		return this.pseudo;
	}

}
