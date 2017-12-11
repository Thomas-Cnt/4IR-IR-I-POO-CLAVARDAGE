package com.thomascantie.insa.model.core;

public class ConnectionMessage {

	private User user;
	private int portNumber;
	private State state;

	public ConnectionMessage(User user, int port, State state) {
		this.user = user;
		this.portNumber = port;
		this.state = state;
	}

	public ConnectionMessage(String data) {
		int sepState = data.indexOf("--");
		int sepPort = data.indexOf(":");
		this.user = new User(data.substring(0, sepState));
		this.portNumber = Integer.parseInt(data.substring(sepPort+1));
		this.state = State.valueOf(data.substring(sepState+2, sepPort));
	}

	public String getPseudo() {
		return this.user.getPseudo();
	}

	public boolean isConnectionOn() {
		return this.state.equals(State.ON);
	}

	public int getPortNumber() {
		return this.portNumber;
	}

	@Override
	public String toString() {
		return this.user.getPseudo() + "--" + this.state + ":" + this.portNumber;
	}

}
