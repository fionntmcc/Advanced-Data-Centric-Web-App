package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Vehicle;
import com.example.demo.services.VehicleService;
import com.example.demo.views.VehicleViews;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/vehicles")
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
    
    
}