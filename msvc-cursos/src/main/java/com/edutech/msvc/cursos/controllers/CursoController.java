package com.edutech.msvc.cursos.controllers;

import com.edutech.msvc.cursos.models.Curso;
import com.edutech.msvc.cursos.services.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cursos")
@Validated
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.cursoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cursoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Curso> save(@Valid @RequestBody Curso curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cursoService.save(curso));
    }
}

