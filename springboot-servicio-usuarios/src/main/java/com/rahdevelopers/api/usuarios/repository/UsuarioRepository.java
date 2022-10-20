package com.rahdevelopers.api.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahdevelopers.api.usuarios.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	public Optional<UsuarioEntity> findByUserName(String userName);

}
