package com.crud.spring_backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.spring_backend.dtos.EmpleadoDto;
import com.crud.spring_backend.services.EmpleadoService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



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

    @GetMapping("{id}")
    public ResponseEntity<EmpleadoDto> getEmpleadoPorId(@PathVariable("id") Long id){
        EmpleadoDto empleadoDto = empleadoService.getEmpleadoPorId(id);
        return ResponseEntity.ok(empleadoDto);
    }


    @GetMapping
    public ResponseEntity<List<EmpleadoDto>> getEmpleados(){
        List<EmpleadoDto> empleados = empleadoService.getEmpleados();
        return ResponseEntity.ok(empleados);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmpleadoDto> putEmpleado(@PathVariable("id") Long idEmpleado, 
        @RequestBody EmpleadoDto empleadoActualizado){

            EmpleadoDto empleadoDto =  empleadoService.putEmpleado(idEmpleado, empleadoActualizado);
            return ResponseEntity.ok(empleadoDto);
        

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmpleado(@PathVariable("id") Long idEmpleado){
        empleadoService.deleteEmpleado(idEmpleado);
        return ResponseEntity.ok("Empleado eliminado satisfactoriamente");
    }
   
}
