package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    @EntityGraph(attributePaths = {"owner", "mechanic", "mechanic.garage"})
    List<Vehicle> findAll();
    
    @EntityGraph(attributePaths = {"owner", "mechanic", "mechanic.garage"})
    List<Vehicle> findByMake(String make);
    
    @EntityGraph(attributePaths = {"owner", "mechanic", "mechanic.garage"})
    Optional<Vehicle> findByReg(String reg);
}