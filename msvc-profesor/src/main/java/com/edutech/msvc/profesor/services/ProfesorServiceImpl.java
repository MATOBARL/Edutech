package com.edutech.msvc.profesor.services;

import com.edutech.msvc.profesor.exceptions.ProfesorException;
import com.edutech.msvc.profesor.models.Profesor;
import com.edutech.msvc.profesor.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService{
    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public List<Profesor> findAll() { return this.profesorRepository.findAll();
    }

    @Override
    public Profesor findById(Long id) {
        return this.profesorRepository.findById(id).orElseThrow(
                () -> new ProfesorException("El profesor con el id: " + id+" no se encuentra en la base de datos")
        );
    }

    @Override
    public Profesor save(Profesor profesorNew) {
        Profesor profesor = new Profesor();
        profesor.setNombre(profesorNew.getNombre());
        if(this.profesorRepository.findByNombre(profesor.getNombre()).isPresent()) {
            throw new ProfesorException("El profesor con el nombre: " + profesor.getNombre()
                    + " ya existe en la base de datos");
        }
        return this.profesorRepository.save(profesor);
    }

    @Override
    public Profesor updateById(Long id, Profesor profesorUpdated){
        return profesorRepository.findById(id).map( profesor -> {
            profesor.setNombre(profesorUpdated.getNombre());
            // Update
            return profesorRepository.save(profesor);
        }).orElseThrow(
                () -> new ProfesorException("El profesor con el id: " + id+" no se encuentra en la base de datos")
        );
    }

    @Override
    public void deleteById(Long id){
        profesorRepository.deleteById(id);
    }
}
