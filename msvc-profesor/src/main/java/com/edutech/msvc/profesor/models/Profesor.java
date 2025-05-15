package com.edutech.msvc.profesor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name = "profesores")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    private Long idCurso;

    @Column(nullable = false)
    @NotEmpty(message = "El profesor tiene que tener un nombre")
    private String nombre;
}
