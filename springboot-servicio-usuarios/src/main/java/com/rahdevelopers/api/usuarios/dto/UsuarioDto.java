package com.rahdevelopers.api.usuarios.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class UsuarioDto {

	private Long ID;
	private String userName;
	private String userPassword;
	private Boolean enabled;
	private String nombre;
	private String apellidos;
	private String email;
	private LocalDate birthDate;

	private List<RoleDto> roles;
}
