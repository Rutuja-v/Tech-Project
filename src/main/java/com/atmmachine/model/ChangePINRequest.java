package com.atmmachine.model;

//ChangePINRequest.java
public class ChangePINRequest {

	private String pin;
	private String newPIN;


	public String getNewPIN() {
		return newPIN;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public void setNewPIN(String newPIN) {
		this.newPIN = newPIN;
	}

	public ChangePINRequest() {
		super();
	}

	public ChangePINRequest(String pin, String newPIN) {
		
		this.pin=pin;
		this.newPIN = newPIN;
	}

	// Add constructor, getters, and setters as needed.

}
