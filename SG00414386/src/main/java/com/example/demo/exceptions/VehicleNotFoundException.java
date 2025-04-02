package com.example.demo.exceptions;

public class VehicleNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VehicleNotFoundException(String message) {
        super(message);
    }
}
