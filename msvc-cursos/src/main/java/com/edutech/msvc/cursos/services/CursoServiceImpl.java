package com.edutech.msvc.cursos.services;

import com.edutech.msvc.cursos.exceptions.CursoException;
import com.edutech.msvc.cursos.models.Curso;
import com.edutech.msvc.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService{
    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public List<Curso> findAll() { return this.cursoRepository.findAll();
    }

    @Override
    public Curso findById(Long id) {
        return this.cursoRepository.findById(id).orElseThrow(
                () -> new CursoException("La prevision con el id: " + id+" no se encuentra en la base de datos")
        );
    }

    @Override
    public Curso save(Curso curso) {
        if(this.cursoRepository.findByContenido(curso.getContenido()).isPresent()) {
            throw new CursoException("La prevision con el nombre: " + curso.getContenido()
                    + " ya existe en la base de datos");
        }
        return this.cursoRepository.save(curso);
    }
}
