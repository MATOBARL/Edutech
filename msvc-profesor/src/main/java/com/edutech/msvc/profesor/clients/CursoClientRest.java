package com.edutech.msvc.profesor.clients;

import com.edutech.msvc.profesor.models.Curso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-cursos",url = "localhost:8011/api/v1/cursos")
public interface CursoClientRest {

    @GetMapping("/{id}")
    List<Curso> findByIdProfesor(@PathVariable Long id);
}
