package com.rahdevelopers.api.usuarios.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.rahdevelopers.api.usuarios.entity.RoleEntity;

public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Long> {

}
