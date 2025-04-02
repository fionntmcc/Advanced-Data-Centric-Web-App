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
@RequestMapping("/api/vehicle")
@Validated(VehiclePOSTValidation.class) // Move to class level
public class VehicleController {
    
    @Autowired
    VehicleService vs;
    
    @GetMapping("/all")
    @JsonView(VehicleViews.Public.class)
    public Iterable<Vehicle> getAllVehicles() {
        return vs.getAllVehicles();
    }
    
    @GetMapping
    @JsonView(VehicleViews.Public.class)
    public List<Vehicle> getVehiclesByMake(@RequestParam String make) {
        return vs.getVehiclesByMake(make);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Vehicle addVehicle(@Valid @RequestBody Vehicle v) {
        try {
            // Validation happens automatically before this line
            vs.save(v);
            return v;
        } catch (VehicleException vx) {
            // Handle business logic errors (e.g., duplicate reg)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, vx.getMessage());
        } catch (Exception e) {
            // Catch validation failures (throws 500)
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                "Validation failed: " + e.getMessage()
            );
        }
    }
}