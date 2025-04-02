package com.example.demo.exceptions;

public class VehicleException extends Exception{
	
	private String message;
	
	public VehicleException(String message) {
		super(message);
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
