package com.edutech.msvc.cursos.repositories;

import com.edutech.msvc.cursos.models.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByContenido(String contenido);
    List<Curso> findByIdProfesor(Long idProfesor);
}
