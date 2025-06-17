package com.edutech.msvc.profesor.controllers;

import com.edutech.msvc.profesor.models.entities.Profesor;
import com.edutech.msvc.profesor.services.ProfesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Profesores", description = "Esta secciób contiene los CRUD de profesores")
public class ProfesorController {
    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    @Operation(
            summary = "Devuelve todos los profesores",
            description = "Este metodo debe retornar un List de Profesor," +
                    "en caso de que no encuentre nada, retorna una List vacia"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornaron todos los profesores.")
    })
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