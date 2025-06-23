package com.edutech.msvc.profesor.init;

import com.edutech.msvc.profesor.models.entities.Profesor;
import com.edutech.msvc.profesor.repositories.ProfesorRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Profile("dev")
@Component
public class LoadDatebase implements CommandLineRunner {

    @Autowired
    private ProfesorRepository profesorRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDatebase.class);


    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es", "CL"));

        if(profesorRepository.count()==0){
            for(int i=0;i<1000;i++){
                Profesor profesor = new Profesor();
                profesor.setNombre(faker.name().fullName());

                profesor = profesorRepository.save(profesor);
                logger.info("El profesor creado es: {}", profesor);
            }
        }
    }
}
