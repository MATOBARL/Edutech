package com.edutech.msvc.profesor.services;

import com.edutech.msvc.profesor.clients.CursoClientRest;
import com.edutech.msvc.profesor.dtos.CursoProfesorDTO;
import com.edutech.msvc.profesor.exceptions.ProfesorException;
import com.edutech.msvc.profesor.models.Curso;
import com.edutech.msvc.profesor.models.entities.Profesor;
import com.edutech.msvc.profesor.repositories.ProfesorRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService{
    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private CursoClientRest cursoClientRest;

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

    @Override
    public List<CursoProfesorDTO> findCursosById(Long profesorId) {
        // Agregamos esto en caso que no exista el profesor que estamos buscado la app pueda realizar la excepcion
        Profesor profesor = this.findById(profesorId);
        // Con esto podemos obtener le listado de cursos que posee el profesor
        List<Curso> atenciones = this.cursoClientRest.findByIdProfesor(profesor.getIdProfesor());

        // De esta forma nos aseguramos que exista el listado de cursos de un profesor si no no tiene sentido
        // realizar el procesamiento de información
        if(!cursos.isEmpty()){
            return cursos.stream().map(curso -> {

                CursoProfesorDTO dto = new CursoProfesorDTO();
                dto.setIdProfesor(profesorId);
                dto.setContenido(curso.setContenido());

                return dto;
            }).toList();
        }

        return List.of();
    }
}
