package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.views.VehicleViews;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonView(VehicleViews.Public.class)
	@Column(unique = true)
	private String cid;
	
	@JsonView(VehicleViews.Public.class)
	private String name;
	
	private String phone;
	
	@OneToMany(mappedBy = "owner")
	private List<Vehicle> vehicles = new ArrayList<>();
	
	public Customer() {
		super();
	}
	
	public Customer(String cid, String name, String phone, List<Vehicle> vehicles) {
		super();
		this.cid = cid;
		this.name = name;
		this.phone = phone;
		this.vehicles = vehicles;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
}
