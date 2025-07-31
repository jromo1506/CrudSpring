package com.crud.spring_backend.controller;

import com.crud.spring_backend.controllers.EmpleadoController;
import com.crud.spring_backend.dtos.EmpleadoDto;
import com.crud.spring_backend.services.EmpleadoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmpleadoController.class)

public class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpleadoService empleadoService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void crearEmpleado_deberiaRetornar201() throws Exception {
        EmpleadoDto request = new EmpleadoDto(null, "Juan", "Perez", "juan@mail.com");
        EmpleadoDto response = new EmpleadoDto(1L, "Juan", "Perez", "juan@mail.com");

        when(empleadoService.crearEmpleado(any())).thenReturn(response);

        mockMvc.perform(post("/api/empleados")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getEmpleadoPorId_deberiaRetornar200() throws Exception {
        EmpleadoDto response = new EmpleadoDto(1L, "Ana", "Lopez", "ana@mail.com");

        when(empleadoService.getEmpleadoPorId(1L)).thenReturn(response);

        mockMvc.perform(get("/api/empleados/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName").value("Ana"));
    }

    @Test
    void getEmpleados_deberiaRetornarLista() throws Exception {
        List<EmpleadoDto> empleados = List.of(
            new EmpleadoDto(1L, "Juan", "P", "j@mail.com"),
            new EmpleadoDto(2L, "Ana", "L", "a@mail.com")
        );

        when(empleadoService.getEmpleados()).thenReturn(empleados);

        mockMvc.perform(get("/api/empleados"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void putEmpleado_deberiaRetornar200() throws Exception {
        EmpleadoDto request = new EmpleadoDto(null, "Nuevo", "Apellido", "nuevo@mail.com");
        EmpleadoDto response = new EmpleadoDto(1L, "Nuevo", "Apellido", "nuevo@mail.com");

        when(empleadoService.putEmpleado(any(), any())).thenReturn(response);

        mockMvc.perform(put("/api/empleados/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName").value("Nuevo"));
    }

    @Test
    void deleteEmpleado_deberiaRetornar200() throws Exception {
        mockMvc.perform(delete("/api/empleados/1"))
            .andExpect(status().isOk())
            .andExpect(content().string("Empleado eliminado satisfactoriamente"));
    }

}
