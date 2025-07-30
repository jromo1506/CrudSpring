package com.crud.spring_backend.services.impl;

import org.springframework.stereotype.Service;

import com.crud.spring_backend.dtos.EmpleadoDto;
import com.crud.spring_backend.mapper.EmpleadoMapper;
import com.crud.spring_backend.models.Empleado;
import com.crud.spring_backend.repository.EmpleadoRepository;
import com.crud.spring_backend.services.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private EmpleadoRepository empleadoRepository;

    
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public EmpleadoDto crearEmpleado(EmpleadoDto empleadoDto){
        Empleado empleado  = EmpleadoMapper.mapearEmpleado(empleadoDto);
        Empleado empleadoGuardado = empleadoRepository.save(empleado);
        return EmpleadoMapper.mapearDtoEmpleado(empleadoGuardado);
    }
    
}
