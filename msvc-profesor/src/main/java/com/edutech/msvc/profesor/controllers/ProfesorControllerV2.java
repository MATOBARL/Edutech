package com.edutech.msvc.profesor.controllers;

import com.edutech.msvc.profesor.assemblers.CursoProfesorDTOModelAssembler;
import com.edutech.msvc.profesor.assemblers.ProfesorModelAssembler;
import com.edutech.msvc.profesor.dtos.CursoProfesorDTO;
import com.edutech.msvc.profesor.dtos.ErrorDTO;
import com.edutech.msvc.profesor.models.entities.Profesor;
import com.edutech.msvc.profesor.services.ProfesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/profesor")
@Validated
@Tag(
        name = "Profesor API HATEOAS",
        description = "Aquí se generan todos los métodos CRUD para profesor"
)
public class ProfesorControllerV2 {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private ProfesorModelAssembler profesorModelAssembler;

    @Autowired
    private CursoProfesorDTOModelAssembler cursoProfesorDTOModelAssembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(
            summary = "Endpoint que obtiene todos los profesores",
            description = "Este endpoint devuelve en un List todos los profesores que se encuentren " +
                    "en la base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion de extración de profesores exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Profesor.class)
                    )
            )
    })
    public ResponseEntity<CollectionModel<EntityModel<Profesor>>> findAll() {

        List<EntityModel<Profesor>> entityModels = this.profesorService.findAll()
                .stream()
                .map(profesorModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Profesor>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(ProfesorControllerV2.class).findAll()).withSelfRel()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(
            summary = "Endpoint que devuelve un profesor por id",
            description = "Endpoint que va devolver un Profesor.class al momento de buscarlo por id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obtención por id correcta",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Profesor.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error cuando el medico con cierto id no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                            //examples = @ExampleObject(
                            //        name = "ERROR NOT FOUND",
                            //        value = "{\"status\":\"200\", \"error\":\"medico no encontrado\"}"
                            //)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(
                    name = "id",
                    description = "Primary Key - Entidad profesor",
                    required = true
            )

    })
    public ResponseEntity<EntityModel<Profesor>> findById(@PathVariable Long id) {
        EntityModel<Profesor> entityModel = this.profesorModelAssembler.toModel(
                this.profesorService.findById(id)
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(
            summary = "Endpoint guardado de un profesor",
            description = "Endpoint que me permite capturar un elemento Profesor.class y lo guarda " +
                    "dentro de nuestra base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Creacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Profesor.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Algun elemento de un msvc no se encuentra",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "El elemento que intentas crear ya existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Estructura de datos que me permite realizar la creación de un profesor",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Profesor.class)
            )
    )
    public ResponseEntity<EntityModel<Profesor>>  create(@Valid @RequestBody Profesor profesor) {
        Profesor profesorNew = this.profesorService.save(profesor);
        EntityModel<Profesor> entityModel = this.profesorModelAssembler.toModel(profesorNew);
        return ResponseEntity
                .created(linkTo(methodOn(ProfesorControllerV2.class).findById(profesorNew.getIdProfesor())).toUri())
                .body(entityModel);
    }


    @GetMapping(value = "/{id}/cursos", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(
            summary = "Endpoint que devuelve un profesor por id",
            description = "Endpoint que va devolver un Profesor.class al momento de buscarlo por id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obtención por id correcta",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = CursoProfesorDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error cuando el profesor con cierto id no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                            //examples = @ExampleObject(
                            //        name = "ERROR NOT FOUND",
                            //        value = "{\"status\":\"200\", \"error\":\"profesor no encontrado\"}"
                            //)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(
                    name = "id",
                    description = "Primary Key - Entidad profesor",
                    required = true
            )

    })
    public ResponseEntity<CollectionModel<EntityModel<CursoProfesorDTO>>> findCursosById(@PathVariable Long id) {
        List<EntityModel<CursoProfesorDTO>> entityModels = this.profesorService.findCursosById(id)
                .stream()
                .map(cursoProfesorDTOModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<CursoProfesorDTO>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(ProfesorControllerV2.class).findCursosById(id)).withRel("cursos-profesor")
        );
        return ResponseEntity.status(HttpStatus.OK).body(collectionModel);
    }
}
