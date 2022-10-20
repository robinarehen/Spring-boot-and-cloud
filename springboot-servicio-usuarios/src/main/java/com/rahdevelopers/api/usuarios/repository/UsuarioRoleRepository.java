package com.rahdevelopers.api.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahdevelopers.api.usuarios.entity.UsuarioRoleEntity;

public interface UsuarioRoleRepository extends JpaRepository<UsuarioRoleEntity, Long> {

}
