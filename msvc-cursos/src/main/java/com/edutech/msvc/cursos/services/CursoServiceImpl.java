package com.edutech.msvc.cursos.services;

import com.edutech.msvc.cursos.clients.ProfesorClientRest;
import com.edutech.msvc.cursos.exceptions.CursoException;
import com.edutech.msvc.cursos.models.entities.Curso;
import com.edutech.msvc.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfesorClientRest profesorClientRest;

    @Override
    public List<Curso> findAll() { return this.cursoRepository.findAll();
    }

    @Override
    public Curso findById(Long id) {
        return this.cursoRepository.findById(id).orElseThrow(
                () -> new CursoException("El curso con el id: " + id+" no se encuentra en la base de datos")
        );
    }

    @Override
    public Curso save(Curso cursoNew) {
        Curso curso = new Curso();
        curso.setContenido(cursoNew.getContenido());
        if(this.cursoRepository.findByContenido(curso.getContenido()).size()>0) {
            throw new CursoException("El curso con el contenido: " + curso.getContenido()
                    + " ya existe en la base de datos");
        }
        return this.cursoRepository.save(curso);
    }

    @Override
    public Curso updateById(Long id, Curso cursoUpdated){
        return cursoRepository.findById(id).map( curso -> {
            curso.setContenido(cursoUpdated.getContenido());
            // Update
            return cursoRepository.save(curso);
        }).orElseThrow(
                () -> new CursoException("El curso con el id: " + id+" no se encuentra en la base de datos")
        );
    }

    @Override
    public void deleteById(Long id){
        cursoRepository.deleteById(id);
    }

    public List<Curso> findbyProfesorId(Long profesorId) {
        return this.cursoRepository.findByIdProfesor(profesorId);
    }
}
