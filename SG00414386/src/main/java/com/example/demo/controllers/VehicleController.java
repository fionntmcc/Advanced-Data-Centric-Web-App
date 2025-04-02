package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;

import com.example.demo.exceptions.VehicleException;
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
    
    @GetMapping("/all") // GET "/api.vehicle/all"
    @JsonView(VehicleViews.Public.class)
    public Iterable<Vehicle> getAllVehicles() {
        return vs.getAllVehicles(); // Call service to return JSON list.
    }
    
    @GetMapping // GET "/api/vehicle?make=<carMake>"
    @JsonView(VehicleViews.Public.class)
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
}