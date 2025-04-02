package com.example.demo.exceptions;

public class MechanicNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MechanicNotFoundException(String message) {
        super(message);
    }
}