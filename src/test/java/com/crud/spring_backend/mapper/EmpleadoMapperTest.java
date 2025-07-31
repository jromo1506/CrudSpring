package com.crud.spring_backend.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.crud.spring_backend.dtos.EmpleadoDto;
import com.crud.spring_backend.models.Empleado;

public class EmpleadoMapperTest {
    
    @Test
    void mapearDtoEmpleado_deberiaMapearCorrectamente() {
        Empleado empleado = new Empleado(1L, "Juan", "Perez", "juan@mail.com");

        EmpleadoDto dto = EmpleadoMapper.mapearDtoEmpleado(empleado);

        assertEquals(1L, dto.getId());
        assertEquals("Juan", dto.getFirstName());
        assertEquals("Perez", dto.getLastName());
        assertEquals("juan@mail.com", dto.getEmail());
    }

    @Test
    void mapearEmpleado_deberiaMapearCorrectamente() {
        EmpleadoDto dto = new EmpleadoDto(2L, "Ana", "Lopez", "ana@mail.com");

        Empleado empleado = EmpleadoMapper.mapearEmpleado(dto);

        assertEquals(2L, empleado.getId());
        assertEquals("Ana", empleado.getFirstName());
        assertEquals("Lopez", empleado.getLastName());
        assertEquals("ana@mail.com", empleado.getEmail());
    }
}
