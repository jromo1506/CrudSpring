package com.crud.spring_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmpleadoDto {
    private Long  id;
    private String firstName;
    private String lastName;
    private String email;


}
