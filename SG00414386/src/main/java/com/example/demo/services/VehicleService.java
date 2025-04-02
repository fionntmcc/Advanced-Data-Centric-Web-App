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
    private VehicleRepository vr;
    
    @JsonView(VehicleViews.Public.class)
    public Iterable<Vehicle> getAllVehicles() {
        return vr.findAll();
    }
    
    @JsonView(VehicleViews.Public.class)
    public List<Vehicle> getVehiclesByMake(String make) {
        return vr.findByMake(make);
    }
    
    public void save(Vehicle v) throws VehicleException {
    	 try {
    	 vr.save(v);
    	 } catch (DataIntegrityViolationException ex) {
    	 throw new VehicleException("Vehicle " + v.getReg() + " already exists");
    	 }
    }
}