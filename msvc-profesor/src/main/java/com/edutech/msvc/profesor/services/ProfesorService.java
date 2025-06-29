package com.edutech.msvc.profesor.services;

import com.edutech.msvc.profesor.dtos.CursoProfesorDTO;
import com.edutech.msvc.profesor.models.entities.Profesor;

import java.util.List;

public interface ProfesorService {
    List<Profesor> findAll();
    Profesor findById(Long id);
    Profesor save(Profesor profesorNew);
    Profesor updateById(Long id, Profesor profesorUpdated);
    void deleteById(Long id);
    List<CursoProfesorDTO> findCursosById(Long profesorId);
}
