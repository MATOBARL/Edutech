package com.edutech.msvc.cursos.clients;

import com.edutech.msvc.cursos.models.Profesor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-profesor",url = "localhost:8012/api/v1/profesor")
public interface ProfesorClientRest {

    @GetMapping("/{id}")
    Profesor findById(@PathVariable Long id);
}
