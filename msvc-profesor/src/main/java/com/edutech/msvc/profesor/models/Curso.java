package com.edutech.msvc.profesor.models;

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Curso {
    private Long idCurso;
    private String contenido;
    private Long idProfesor;
}
