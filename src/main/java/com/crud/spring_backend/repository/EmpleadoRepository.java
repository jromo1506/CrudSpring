package com.crud.spring_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.spring_backend.models.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado,Long>{

}