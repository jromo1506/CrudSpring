package com.crud.spring_backend.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.crud.spring_backend.dtos.EmpleadoDto;
import com.crud.spring_backend.exception.ResourceNotFoundException;
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

    @Override
    public EmpleadoDto getEmpleadoPorId(Long IdEmpleado){
        Empleado empleado =empleadoRepository.findById(IdEmpleado).orElseThrow(
            ()-> new ResourceNotFoundException("Empleado no existe")
        );
        return EmpleadoMapper.mapearDtoEmpleado(empleado);
    }

    @Override
    public List<EmpleadoDto> getEmpleados(){
        List <Empleado> listaEmpleados = empleadoRepository.findAll();
        return listaEmpleados.stream().map((empleado)->EmpleadoMapper.mapearDtoEmpleado(empleado))
        .collect(Collectors.toList());
    }

    @Override
    public EmpleadoDto putEmpleado(Long idEmpleado,EmpleadoDto empleadoDto){
        Empleado empleado = empleadoRepository.findById(idEmpleado).orElseThrow(
            ()-> new ResourceNotFoundException("No se encontro el empleado")
        );
        empleado.setFirstName(empleadoDto.getFirstName());
        empleado.setLastName(empleadoDto.getLastName());
        empleado.setEmail(empleadoDto.getEmail());

        Empleado empleadoActualizado = empleadoRepository.save(empleado);
        return EmpleadoMapper.mapearDtoEmpleado(empleadoActualizado);
    }

    @Override 
    public void deleteEmpleado(Long idEmpleado){
        Empleado empEnc = empleadoRepository.findById(idEmpleado).orElseThrow(
            ()-> new ResourceNotFoundException("No se encontro el usuario")
        );

        empleadoRepository.deleteById(idEmpleado);
    }


    
}
