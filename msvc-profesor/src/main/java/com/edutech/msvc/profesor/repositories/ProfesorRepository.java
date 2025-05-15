package com.edutech.msvc.profesor.repositories;

import com.edutech.msvc.profesor.models.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    Optional<Profesor> findByNombre(String nombre);
}
