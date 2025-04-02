package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Mechanic;

public interface MechanicRepository extends JpaRepository<Mechanic, Integer> {
	@EntityGraph(attributePaths = {"garage"})
    List<Mechanic> findAll();
	
	@EntityGraph(attributePaths = {"garage"})
    Optional<Mechanic> findByMid(String mid);
}
