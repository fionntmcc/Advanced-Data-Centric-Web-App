package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.MechanicNotFoundException;
import com.example.demo.exceptions.VehicleException;
import com.example.demo.exceptions.VehicleNotFoundException;
import com.example.demo.models.Mechanic;
import com.example.demo.models.Vehicle;
import com.example.demo.repositories.MechanicRepository;
import com.example.demo.repositories.VehicleRepository;
import com.example.demo.views.VehicleViews;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.transaction.Transactional;

@Service
public class VehicleService {
    
    @Autowired
    private VehicleRepository vr; // Get repositories for CRUD operations.
    @Autowired
    private MechanicRepository mr;
    
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
    
    public void save(Vehicle v) throws VehicleException { // Save vehicle to database.
    	 try {
    	 vr.save(v);
    	 } catch (DataIntegrityViolationException ex) {
    	 throw new VehicleException("Vehicle " + v.getReg() + " already exists"); // For correct HttpResponseStatus and Message.
    	 }
    }
    
    @Transactional // PUT code is atomic. All or nothing.
    public Vehicle updateMechanic(String reg, String mid) { // For POST method: Update mechanic associated with car reg.
        Vehicle vehicle = vr.findByReg(reg)
            .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found")); // Error handling.
        
        Mechanic mechanic = mr.findByMid(mid)
            .orElseThrow(() -> new MechanicNotFoundException("Mechanic not found")); // Error handling.
        
        vehicle.setMechanic(mechanic); // Update mechanic.
        return vr.save(vehicle); // Save mechanic.
    }
}