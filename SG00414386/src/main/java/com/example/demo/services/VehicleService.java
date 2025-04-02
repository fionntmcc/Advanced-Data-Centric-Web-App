package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.VehicleException;
import com.example.demo.models.Vehicle;
import com.example.demo.repositories.VehicleRepository;
import com.example.demo.views.VehicleViews;
import com.fasterxml.jackson.annotation.JsonView;

@Service
public class VehicleService {
    
    @Autowired
    private VehicleRepository vr; // Get repository for CRUD operations.
    
    @JsonView(VehicleViews.Public.class) // Use JsonView for returning vehicle objects.
    									 // This only returns the fields marked with the @JsonView annotation.
    									 // @JsonIgnore is used to avoid recursion (i.e. Vehicle -> Customer -> Mechanic
    									 // -> Garage -> Mechanic...)
    public Iterable<Vehicle> getAllVehicles() {
        return vr.findAll();
    }
    
    @JsonView(VehicleViews.Public.class) // Returns JsonView objects.
    public List<Vehicle> getVehiclesByMake(String make) {
        return vr.findByMake(make);
    }
    
    public void save(Vehicle v) throws VehicleException {
    	 try {
    	 vr.save(v);
    	 } catch (DataIntegrityViolationException ex) {
    	 throw new VehicleException("Vehicle " + v.getReg() + " already exists"); // For correct HttpResponseStatus and Message.
    	 }
    }
}