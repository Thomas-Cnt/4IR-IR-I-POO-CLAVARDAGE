package com.thomascantie.insa.model.core;

public class ConnectionInfo {

	private String ipAddress;
	private int portNumber;

	public ConnectionInfo(String ipAddress, int portNumber) {
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
	}

	public String getIPAddress() {
		return this.ipAddress;
	}

	public int getPortNumber() {
		return this.portNumber;
	}

}
