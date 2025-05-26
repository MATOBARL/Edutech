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
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.cursoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.cursoService.findById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findByIdProfesor(@PathVariable Long idProfesor) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.cursoService.findById(idProfesor));
    }

    @PostMapping
    public ResponseEntity<Curso> save(@Valid @RequestBody Curso cursoNew) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.cursoService.save(cursoNew));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateById(@PathVariable Long id, @RequestBody Curso cursoUpdated){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cursoService.updateById(id,cursoUpdated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        cursoService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}

