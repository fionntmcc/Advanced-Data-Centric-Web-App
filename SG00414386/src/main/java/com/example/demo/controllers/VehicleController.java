package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.example.demo.exceptions.VehicleException;
import com.example.demo.models.Vehicle;
import com.example.demo.services.VehicleService;
import com.example.demo.validations.VehiclePOSTValidation;
import com.example.demo.views.VehicleViews;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehicle")
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
    
    @Validated(VehiclePOSTValidation.class)
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addEmployee(@Valid @RequestBody Vehicle v) {
    	try {
    		vs.save(v);
    	} catch (VehicleException vx) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, vx.getMessage());
    	}
    }
}