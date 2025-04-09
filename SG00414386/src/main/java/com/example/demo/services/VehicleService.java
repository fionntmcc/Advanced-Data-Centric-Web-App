package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTOs.VehicleDTO;
import com.example.demo.exceptions.MechanicNotFoundException;
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
    
    @JsonView(VehicleViews.ExtendedPublic.class) // Use JsonView for returning vehicle objects.
    									 // This only returns the fields marked with the @JsonView ExtendedPublic annotation.
    									 // @JsonManagedReference used to keep track of Serialisation
    									 // @JsonIgnore is used to avoid recursion (i.e. Vehicle -> Customer -> Mechanic
    									 // -> Garage -> Mechanic...)
    public Iterable<Vehicle> getAllVehicles() {
        return vr.findAll();
    }
    
    @JsonView(VehicleViews.ExtendedPublic.class) // Returns all fields.
    public Optional<Vehicle> getVehicleByReg(String reg) { // Optional as Vehicle may not exist with given reg.
        return vr.findByReg(reg);
    }
    
    @JsonView(VehicleViews.ExtendedPublic.class) // Returns all fields.
    public List<Vehicle> getVehiclesByMake(String make) { // List for One to Many mapping.
        return vr.findByMake(make);
    }
    
    @JsonView(VehicleViews.Public.class) // Returns basic fields.
    public List<Vehicle> getVehiclesByMechanicId(String mechanic) { // List for One to Many mapping.
        return vr.findByMechanicId(Integer.parseInt(mechanic.substring(1))); // Remove 'M' from the start of mechanic id and parse to Integer.
        																	 // Matches mechanic table to vehicle table.
    }
    
    // The below save method worked if @JsonManagedReference and JsonBackReference were omitted.
    // DTO proved easier to implement for POST method -> standard in industry.
    /*
    @JsonView(VehicleViews.Minimal.class) // Only contains reg, make, model.
    public void save(Vehicle v) throws VehicleException { // Save vehicle to database.
    	 try {
    	 vr.save(v);
    	 } catch (DataIntegrityViolationException ex) {
    	 throw new VehicleException("Vehicle " + v.getReg() + " already exists"); // For correct HttpResponseStatus and Message.
    	 }
    }
     */
    
    // Creates a Vehicle from DTO for POST implementation.
    public Vehicle createVehicleFromDTO(VehicleDTO dto) {
    	if (vr.existsByReg(dto.getReg())) {
            throw new IllegalArgumentException("A vehicle with this registration already exists");
        }
    	
        Vehicle vehicle = new Vehicle();
        vehicle.setMake(dto.getMake());
        vehicle.setModel(dto.getModel());
        vehicle.setReg(dto.getReg());

        return vr.save(vehicle);
    }
    
    @Transactional // PUT method is atomic. All or nothing.
    public Vehicle updateMechanic(String reg, String mid) { // For POST method: Update mechanic associated with car reg.
        Vehicle vehicle = vr.findByReg(reg)
            .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found")); // Error handling.
        
        Mechanic mechanic = mr.findByMid(mid)
            .orElseThrow(() -> new MechanicNotFoundException("Mechanic not found")); // Error handling.
        
        vehicle.setMechanic(mechanic); // Update mechanic.
        return vr.save(vehicle); // Save mechanic.
    }
}