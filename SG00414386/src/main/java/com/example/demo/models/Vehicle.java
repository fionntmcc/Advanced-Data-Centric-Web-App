package com.example.demo.models;

import com.example.demo.views.VehicleViews;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import com.example.demo.validations.VehiclePOSTValidation;

@Entity
public class Vehicle {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "reg must be provided", 
			groups = VehiclePOSTValidation.class)
	@JsonView(VehicleViews.Public.class)
	@Column(unique = true)
	private String reg;
	
	
	@NotNull(message = "make must be provided", 
			groups = VehiclePOSTValidation.class)
	@JsonView(VehicleViews.Public.class)
	private String make;
	
	@NotNull(message = "model must be provided", 
			groups = VehiclePOSTValidation.class)
	@JsonView(VehicleViews.Public.class)
	private String model;
	
	@JsonView(VehicleViews.ExtendedPublic.class)
	@ManyToOne
	@JsonManagedReference("vehicle-owner")
	private Customer owner;
	
	@JsonView(VehicleViews.Public.class)
	@ManyToOne
	@JsonManagedReference("vehicle-mechanic")
	private Mechanic mechanic;
	
	
	public Vehicle() {
		super();
	}


	public Vehicle(String reg, String make, String model, Customer owner, Mechanic mechanic) {
		super();
		this.reg = reg;
		this.make = make;
		this.model = model;
		this.owner = owner;
		this.mechanic = mechanic;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


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


	public Customer getOwner() {
		return owner;
	}


	public void setOwner(Customer owner) {
		this.owner = owner;
	}


	public Mechanic getMechanic() {
		return mechanic;
	}


	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}
	
}
