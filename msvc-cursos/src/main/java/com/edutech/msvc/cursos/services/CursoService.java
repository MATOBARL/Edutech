package com.edutech.msvc.cursos.services;

import com.edutech.msvc.cursos.models.Curso;

import java.util.List;

public interface CursoService {
    List<Curso> findAll();
    Curso findById(Long id);
    Curso save(Curso cursoNew);
    Curso updateById(Long id, Curso cursoUpdated);
    void deleteById(Long id);
}
