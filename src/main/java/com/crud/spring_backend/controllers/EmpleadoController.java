package com.crud.spring_backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.spring_backend.dtos.EmpleadoDto;
import com.crud.spring_backend.services.EmpleadoService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@AllArgsConstructor
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    private EmpleadoService empleadoService;

   @PostMapping
    public ResponseEntity<EmpleadoDto> crearEmpleado(@RequestBody EmpleadoDto empleadoDto){
        EmpleadoDto empleadoGuardado = empleadoService.crearEmpleado(empleadoDto);
        return new ResponseEntity<>(empleadoGuardado,HttpStatus.CREATED);
    }
}
