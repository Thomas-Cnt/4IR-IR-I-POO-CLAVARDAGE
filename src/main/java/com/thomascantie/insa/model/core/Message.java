package com.thomascantie.insa.model.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

/**
 * Classe représentant un message
 */
public class Message {

	/**
	 * Utilisateur destinataire
	 */
	private User userTo;
	/**
	 * Contenu du message
	 */
	private String content;
	/**
	 * Date du message
	 */
	private Date date;

	/**
	 * Constructeur
	 *
	 * @param date    date du message
	 * @param userTo  utilisateur destinataire du message
	 * @param content contenu du message
	 */
	public Message(Date date, User userTo, String content) {
		this.date = date;
		this.userTo = userTo;
		this.content = content;
	}

	/**
	 * Constructeur
	 * Créé un message à partir d'une chaîne formatée
	 *
	 * @param data les données du message formatée
	 */
	public Message(String data) {
		int sepPseudo = data.indexOf(" :: ");
		int sepContent = data.indexOf(" > ");

		System.out.println("**************** " + data);

		this.date = new Date(Long.parseLong(data.substring(0, sepPseudo)));
		this.userTo = new User(data.substring(sepPseudo + 4, sepContent));
		this.content = data.substring(sepContent + 3);
	}

	/**
	 * Accesseur en lecture
	 *
	 * @return le message formaté pour l'envoie via le réseau
	 */
	public String formatToSend() {
		return this.date.getTime() + " :: " + this.userTo + " > " + this.content;
	}

	/**
	 * Accesseur en lecture
	 *
	 * @return le contenu du message
	 */
	public String getContent() {
		return this.content;
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd-MM-YY HH:mm");
		return df.format(this.date) + " :: " + this.userTo + " > " + this.content;
	}

}
