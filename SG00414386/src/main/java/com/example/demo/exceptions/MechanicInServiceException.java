package com.example.demo.exceptions;

public class MechanicInServiceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MechanicInServiceException(String message) {
        super(message);
	}
}
