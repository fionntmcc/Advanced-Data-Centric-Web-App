package com.example.demo.exceptions;

public class VehicleException extends Exception{
	public VehicleException(String s) {
		super(s);
	}
	
	public String getMessage() {
		return "Error in posting vehicle";
	}
}
