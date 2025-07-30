package com.crud.spring_backend.mapper;

import com.crud.spring_backend.dtos.EmpleadoDto;
import com.crud.spring_backend.models.Empleado;

public class EmpleadoMapper {

    public static EmpleadoDto mapearDtoEmpleado(Empleado empleado){
        return new EmpleadoDto( 
            empleado.getId(),
            empleado.getFirstName(),
            empleado.getLastName(),
            empleado.getEmail()
        );
    }

    public static Empleado mapearEmpleado(EmpleadoDto empleadoDto){
        return new Empleado(
            empleadoDto.getId(),
            empleadoDto.getFirstName(),
            empleadoDto.getLastName(),
            empleadoDto.getEmail()
        );
    }
    
}
