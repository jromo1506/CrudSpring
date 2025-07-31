package com.crud.spring_backend.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crud.spring_backend.dtos.EmpleadoDto;
import com.crud.spring_backend.exception.ResourceNotFoundException;
import com.crud.spring_backend.models.Empleado;
import com.crud.spring_backend.repository.EmpleadoRepository;
import com.crud.spring_backend.services.impl.EmpleadoServiceImpl;


@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceImplTest {
        @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoServiceImpl empleadoServiceImpl;

    //POST
    @Test
    void crearEmpleado_deberiaGuardarYRetornarEmpleado(){
        EmpleadoDto dto = new EmpleadoDto(null, "Juan", "Perez", "juan@mail.com");
        Empleado empleado = new Empleado(null, "Juan", "Perez", "juan@mail.com");
        Empleado guardado = new Empleado(1L, "Juan", "Perez", "juan@mail.com");

        when(empleadoRepository.save(any())).thenReturn(guardado);

        EmpleadoDto resultado = empleadoServiceImpl.crearEmpleado(dto);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getFirstName());
    }

    //GET BY ID
    @Test
    void getEmpleadoPorId_deberiaRetornarEmpleado(){
          Empleado empleado = new Empleado(1L, "Ana", "Lopez", "ana@mail.com");

        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));

        EmpleadoDto resultado = empleadoServiceImpl.getEmpleadoPorId(1L);

        assertEquals("Ana", resultado.getFirstName());
    }

    //GET ALL
    @Test
    void getEmpleados_deberiaRetornarLista(){
        List<Empleado> lista = List.of(
            new Empleado(1L,"A","B","a@mail.com"),
            new Empleado(2L,"A","B","b@mail.com")

        );

        when(empleadoRepository.findAll()).thenReturn(lista);
        List<EmpleadoDto> resultado  = empleadoServiceImpl.getEmpleados();
        assertEquals(2,resultado.size());
    }

    //PUT
    @Test
    void putEmpleado_deberiaActualizarYRetornarEmpleado(){
          Empleado existente = new Empleado(1L, "Antiguo", "Apellido", "viejo@mail.com");
          EmpleadoDto nuevo = new EmpleadoDto(null, "Nuevo", "Apellido", "nuevo@mail.com");
          Empleado actualizado = new Empleado(1L, "Nuevo", "Apellido", "nuevo@mail.com");

        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(empleadoRepository.save(any())).thenReturn(actualizado);

        EmpleadoDto resultado = empleadoServiceImpl.putEmpleado(1L, nuevo);

        assertEquals("Nuevo", resultado.getFirstName());
    }

    @Test
    void deleteEmpleado_deberiaEliminarSinExcepcion() {
        Empleado emp = new Empleado(1L, "X", "Y", "z@mail.com");

        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(emp));
        doNothing().when(empleadoRepository).deleteById(1L);

        assertDoesNotThrow(() -> empleadoServiceImpl.deleteEmpleado(1L));
    }

    @Test
    void getEmpleadoPorId_deberiaLanzarExcepcionSiNoExiste() {
        when(empleadoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> empleadoServiceImpl.getEmpleadoPorId(99L));
    }


}
