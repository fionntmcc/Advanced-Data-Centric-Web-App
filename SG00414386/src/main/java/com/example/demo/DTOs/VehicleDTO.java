package com.example.demo.DTOs;

import jakarta.validation.constraints.NotBlank;

// This DTO is used in the vehicle POST method, 
// as standard implementations were causing issues with the
// @JsonManagedReference and @JsonBackReference annotations.

public class VehicleDTO {
	@NotBlank(message = "Reg must be provided")
	private String reg;
	
	@NotBlank(message = "Make must be provided")
	private String make;
	
	@NotBlank(message = "Model must be provided")
	private String model;
	
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	
	
}
