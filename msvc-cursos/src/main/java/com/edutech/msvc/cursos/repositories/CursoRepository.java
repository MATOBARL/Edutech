package com.edutech.msvc.cursos.repositories;

import com.edutech.msvc.cursos.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByContenido(String contenido);
    Optional<Curso> findByIdProfesor(Long idProfesor);
}
