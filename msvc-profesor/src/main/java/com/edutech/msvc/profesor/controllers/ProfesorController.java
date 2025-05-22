package com.edutech.msvc.profesor.controllers;

import com.edutech.msvc.profesor.models.Profesor;
import com.edutech.msvc.profesor.services.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profesor")
@Validated
public class ProfesorController {
    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<Profesor>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.profesorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.profesorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Profesor> save(@Valid @RequestBody Profesor profesor) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.profesorService.save(profesor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> updateById(@PathVariable Long id, @RequestBody Profesor profesorUpdated){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(profesorService.updateById(id,profesorUpdated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        profesorService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}