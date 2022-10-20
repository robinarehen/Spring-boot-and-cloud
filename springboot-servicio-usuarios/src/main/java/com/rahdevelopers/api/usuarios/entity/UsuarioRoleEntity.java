package com.rahdevelopers.api.usuarios.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "usuarios_roles", uniqueConstraints = { @UniqueConstraint(columnNames = { "usuario_id", "role_id" }) })
public class UsuarioRoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@Column(name = "usuario_id")
	private Long usuario;

	@Column(name = "role_id")
	private Long role;
}
