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
		this.portNumber = Integer.parseInt(data.substring(sepState+2, sepPort));
		this.state = State.valueOf(data.substring(sepPort+2));
	}

	public String getPseudo() {
		return this.user.getPseudo();
	}

	public boolean isConnectionOn() {
		return this.state.equals(State.ON);
	}

	@Override
	public String toString() {
		return this.user.getPseudo() + "--" + this.state + ":" + this.portNumber;
	}

}
