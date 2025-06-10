package com.edutech.msvc.profesor.services;

import com.edutech.msvc.profesor.exceptions.ProfesorException;
import com.edutech.msvc.profesor.models.Profesor;
import com.edutech.msvc.profesor.repositories.ProfesorRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ProfesorServiceTest {

    @Mock
    private ProfesorRepository profesorRepository;


    @InjectMocks
    private ProfesorServiceImpl profesorService;

    private Profesor profesorPrueba;

    private List<Profesor> profesores = new ArrayList<>();

    @BeforeEach
    public void setup(){

        Faker faker = new Faker(Locale.of("es","CL"));
        for(int i=0;i<100;i++){
            Profesor profesor = new Profesor();
            profesor.setIdProfesor((long) i);
            profesor.setNombre(faker.name().fullName());

            this.profesores.add(profesor);
        }
        this.profesorPrueba = new Profesor(
                1L, "Pablo Contreras"
        );

    }

    @Test
    @DisplayName("Debe listar todos los profesores")
    public void shouldFindAllProfesores(){

        this.profesores.add(this.profesorPrueba);

        when(profesorRepository.findAll()).thenReturn(this.profesores);

        List<Profesor> result = profesorService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(this.profesorPrueba);

        verify(profesorRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Debe encontrar un profesor por id")
    public void shouldFindMEdicoById(){
        when(profesorRepository.findById(1L)).thenReturn(Optional.of(this.profesorPrueba));
        Profesor result = profesorService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.profesorPrueba);
        verify(profesorRepository,times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe entregar una excepcion cuando profesor id no exista")
    public void shouldNotFindProfesorById(){
        Long idInexistente = 999L;
        when(profesorRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> {
            profesorService.findById(idInexistente);
        }).isInstanceOf(ProfesorException.class)
                .hasMessageContaining("El profesor con id " + idInexistente
                        + " no se encuentra en la base de datos");
        verify(profesorRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar un nuevo profesor")
    public void shouldSaveProfesor(){
        when(profesorRepository.save(any(Profesor.class))).thenReturn(this.profesorPrueba);
        Profesor result = profesorService.save(this.profesorPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.profesorPrueba);
        verify(profesorRepository, times(1)).save(any(Profesor.class));
    }
}
