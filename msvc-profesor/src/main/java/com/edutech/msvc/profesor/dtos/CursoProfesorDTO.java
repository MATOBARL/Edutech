package com.edutech.msvc.profesor.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


/**
 * Clase DTO para mostrar el listado de atenciones de un medico
 * Revisar que el medico tambien tiene un DTO, no se muestra la clase POJO
 * Recordar que los DTO son elementos que
 */
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class CursoProfesorDTO {
    @JsonIgnore
    private Long idProfesor;
    private String contenido;
}
