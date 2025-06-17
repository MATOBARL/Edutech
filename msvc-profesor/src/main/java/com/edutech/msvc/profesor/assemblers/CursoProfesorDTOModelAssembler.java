package com.edutech.msvc.profesor.assemblers;

import com.edutech.msvc.profesor.controllers.ProfesorControllerV2;
import com.edutech.msvc.profesor.dtos.CursoProfesorDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CursoProfesorDTOModelAssembler implements RepresentationModelAssembler<CursoProfesorDTO, EntityModel<CursoProfesorDTO>> {
    @Override
    public EntityModel<CursoProfesorDTO> toModel(CursoProfesorDTO entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(ProfesorControllerV2.class).findCursosById(entity.getIdProfesor())).withRel("cursos-profesor")
        );
    }
}
