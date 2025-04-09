package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.exceptions.MechanicInServiceException;
import com.example.demo.exceptions.MechanicNotFoundException;
import com.example.demo.services.MechanicService;

@RestController
@RequestMapping("/api/mechanic")
public class MechanicController {

    @Autowired
    private MechanicService ms;

    @DeleteMapping("/{mid}")
    public ResponseEntity<?> deleteMechanic(@PathVariable String mid) {
        try { // Try to delete mechanic.
            ms.deleteMechanic(mid);
            return ResponseEntity.ok().build();
        } catch (MechanicNotFoundException e) { // Not found.
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, 
                e.getMessage()
            );
        } catch (MechanicInServiceException e) { // Bad request: Mechanic is associated with vehicle/s.
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, 
                e.getMessage()
            );
        }
    }
}