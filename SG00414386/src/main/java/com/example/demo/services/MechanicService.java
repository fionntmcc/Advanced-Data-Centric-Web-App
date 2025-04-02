package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.MechanicInServiceException;
import com.example.demo.exceptions.MechanicNotFoundException;
import com.example.demo.models.Mechanic;
import com.example.demo.models.Vehicle;
import com.example.demo.repositories.MechanicRepository;
import com.example.demo.repositories.VehicleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MechanicService {

    @Autowired
    private MechanicRepository mr;
    
    @Autowired
    private VehicleRepository vr;

    public void deleteMechanic(String mid) {
        // Check if mechanic exists
        Mechanic mechanic = mr.findByMid(mid)
            .orElseThrow(() -> new MechanicNotFoundException("Mechanic with mid " + mid + " not found"));

        // Check if mechanic is associated with any vehicles
        List<Vehicle> vehicles = vr.findByMid(mid);
        if (!vehicles.isEmpty()) {
            throw new MechanicInServiceException("Mechanic is currently servicing " + vehicles.size() + " vehicles");
        }

        // Delete the mechanic
        mr.delete(mechanic);
    }
}