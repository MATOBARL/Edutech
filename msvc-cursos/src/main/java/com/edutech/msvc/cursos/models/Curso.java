package com.edutech.msvc.cursos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "cursos")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long idCurso;

    @Column(nullable = false)
    @NotEmpty(message = "El contenido del curso no puede ser vacio")
    private String contenido;

    @Column(nullable = false)
    @NotNull(message = "El curso debe tener un profesor")
    private Long idProfesor;
}
