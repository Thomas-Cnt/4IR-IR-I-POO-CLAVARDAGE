package com.thomascantie.insa.model.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

public class Message {

	private User userTo;
	private String content;
	private Date date;

	public Message(Date date, User userTo, String content) {
		this.date = date;
		this.userTo = userTo;
		this.content = content;
	}

	public Message(String data) {
		int sepPseudo = data.indexOf(" :: ");
		int sepContent = data.indexOf(" > ");
		this.date = new Date(data.substring(0, sepPseudo));
		this.userTo = new User(data.substring(sepPseudo+4, sepContent));
		this.content = data.substring(sepContent+3);
	}

	public String formatToSend() {
		return this.date.getTime() + " :: " + this.userTo + " > " + this.content;
	}

	public Date getDate() {
		return this.date;
	}

	public User getUserTo() {
		return this.userTo;
	}

	public String getContent() {
		return this.content;
	}

	public String[] buildForCSV() {
		String data = new StringJoiner(";")
				.add(Long.toString(this.date.getTime()))
				.add(this.userTo.toString())
				.add(this.content).toString();
		return data.split(";");
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd-MM-YY HH:mm");
		return df.format(this.date) + " :: " + this.userTo + " > " + this.content;
	}

}
