package com.crud.spring_backend.services;

import java.util.List;

import com.crud.spring_backend.dtos.EmpleadoDto;

public interface EmpleadoService {
    EmpleadoDto crearEmpleado(EmpleadoDto empleadoDto);

    EmpleadoDto getEmpleadoPorId(Long idEmpleado);
   
    List<EmpleadoDto> getEmpleados();

    EmpleadoDto putEmpleado(Long idEmpleado, EmpleadoDto empleadoDto);

    void deleteEmpleado(Long idEmpleado);
}
