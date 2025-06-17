package com.edutech.msvc.profesor.assemblers;

import com.edutech.msvc.profesor.controllers.ProfesorControllerV2;
import com.edutech.msvc.profesor.models.entities.Profesor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProfesorModelAssembler implements RepresentationModelAssembler<Profesor, EntityModel<Profesor>> {

    @Override
    public EntityModel<Profesor> toModel(Profesor entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(ProfesorControllerV2.class).findById(entity.getIdProfesor())).withSelfRel(),
                linkTo(methodOn(ProfesorControllerV2.class).findAll()).withRel("profesor"),
                linkTo(methodOn(ProfesorControllerV2.class).findCursosById(entity.getIdProfesor())).withRel("cursos-profesor"),
                Link.of("http://localhost:8011/cursos/"+entity.getIdProfesor()).withRel("Cursos")
        );
    }
}
