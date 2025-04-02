package com.example.demo.dtos;

public class VehicleDTO {
    private String reg;
    private String make;
    private String model;
    private String ownerCid;      // Customer.cid is String (not Integer)
    private String ownerName;     // Customer.name is String
    private Integer mechanicMid;  // Assuming Mechanic.mid is Integer
    private String mechanicName;  // Mechanic.name is String
    private Double mechanicSalary; // Mechanic.salary is Double
    private String garageGid;     // Garage.gid is String (not Integer)
    private String garageLocation; // Garage.location is String
    private Integer garageBudget;  // Garage.budget is int (or Integer)

    // Constructor
    public VehicleDTO(String reg, String make, String model, 
                     String ownerCid, String ownerName,
                     Integer mechanicMid, String mechanicName, Double mechanicSalary,
                     String garageGid, String garageLocation, Integer garageBudget) {
        this.reg = reg;
        this.make = make;
        this.model = model;
        this.ownerCid = ownerCid;
        this.ownerName = ownerName;
        this.mechanicMid = mechanicMid;
        this.mechanicName = mechanicName;
        this.mechanicSalary = mechanicSalary;
        this.garageGid = garageGid;
        this.garageLocation = garageLocation;
        this.garageBudget = garageBudget;
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

	public String getOwnerCid() {
		return ownerCid;
	}

	public void setOwnerCid(String ownerCid) {
		this.ownerCid = ownerCid;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Integer getMechanicMid() {
		return mechanicMid;
	}

	public void setMechanicMid(Integer mechanicMid) {
		this.mechanicMid = mechanicMid;
	}

	public String getMechanicName() {
		return mechanicName;
	}

	public void setMechanicName(String mechanicName) {
		this.mechanicName = mechanicName;
	}

	public Double getMechanicSalary() {
		return mechanicSalary;
	}

	public void setMechanicSalary(Double mechanicSalary) {
		this.mechanicSalary = mechanicSalary;
	}

	public String getGarageGid() {
		return garageGid;
	}

	public void setGarageGid(String garageGid) {
		this.garageGid = garageGid;
	}

	public String getGarageLocation() {
		return garageLocation;
	}

	public void setGarageLocation(String garageLocation) {
		this.garageLocation = garageLocation;
	}

	public Integer getGarageBudget() {
		return garageBudget;
	}

	public void setGarageBudget(Integer garageBudget) {
		this.garageBudget = garageBudget;
	}

    
}