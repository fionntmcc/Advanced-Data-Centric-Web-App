package com.example.demo.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import com.example.demo.exceptions.MechanicNotFoundException;
import com.example.demo.exceptions.VehicleException;
import com.example.demo.exceptions.VehicleNotFoundException;
import com.example.demo.models.Vehicle;
import com.example.demo.services.VehicleService;
import com.example.demo.validations.VehiclePOSTValidation;
import com.example.demo.views.VehicleViews;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehicle") // API endpoint.
@Validated(VehiclePOSTValidation.class) // Class-level validation.
public class VehicleController {
    
    @Autowired
    VehicleService vs;
    
    // @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all") // GET "/api/vehicle/all"
    @JsonView(VehicleViews.ExtendedPublic.class)
    public Iterable<Vehicle> getAllVehicles() {
        return vs.getAllVehicles(); // Call service to return JSON list.
    }
    
    @GetMapping // GET "/api/vehicle?make=<carMake>"
    @JsonView(VehicleViews.ExtendedPublic.class)
    public List<Vehicle> getVehiclesByMake(@RequestParam String make) {
        return vs.getVehiclesByMake(make); // Call service to return JSON list.
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE) // POST "/api/vehicle"
    public Vehicle addVehicle(@Valid @RequestBody Vehicle v) { // Get vehicle from request body.
        try {
            // Validation happens automatically here.
            vs.save(v);
            return v;
        } catch (VehicleException vx) { // Handle duplicate reg or missing field.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, vx.getMessage());
        } catch (Exception e) {
            // Catch validation failures.
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                "Validation failed: " + e.getMessage()
            );
        }
    }
    
    @PutMapping("/{reg}")
    @JsonView(VehicleViews.Public.class)
    public ResponseEntity<?> updateVehicleMechanic(
        @PathVariable String reg,
        @RequestBody Map<String, Object> requestBody) {  // Use Map to inspect raw JSON
        
        // Check for disallowed attributes
        Set<String> disallowed = Set.of("id", "name", "salary", "garage", "vehicles");
        for (String key : requestBody.keySet()) {
            if (disallowed.contains(key)) {
                throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Attribute not allowed: " + key
                );
            }
        }

        // Validate required 'mid' field
        if (!requestBody.containsKey("mid")) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Required attribute 'mid' is missing"
            );
        }

        try {
            String mid = requestBody.get("mid").toString();
            Vehicle updatedVehicle = vs.updateMechanic(reg, mid);
            return ResponseEntity.ok(updatedVehicle);
        } catch (VehicleNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (MechanicNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}