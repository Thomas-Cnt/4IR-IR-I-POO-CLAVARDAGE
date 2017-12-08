package com.thomascantie.insa.model.core;

public enum State {

	ON("ON"), OFF("OFF");

	private String status;

	State(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return this.status;
	}

}
