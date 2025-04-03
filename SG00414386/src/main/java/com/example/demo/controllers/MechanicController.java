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
        try {
            ms.deleteMechanic(mid);
            return ResponseEntity.ok().build();
        } catch (MechanicNotFoundException e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                e.getMessage()
            );
        } catch (MechanicInServiceException e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                e.getMessage()
            );
        }
    }
}