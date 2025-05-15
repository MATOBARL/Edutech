package com.edutech.msvc.profesor.services;

import com.edutech.msvc.profesor.models.Profesor;

import java.util.List;

public interface ProfesorService {
    List<Profesor> findAll();
    Profesor findById(Long id);
    Profesor save(Profesor profesor);
}
