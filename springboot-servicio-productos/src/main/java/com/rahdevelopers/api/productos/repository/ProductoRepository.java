package com.rahdevelopers.api.productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahdevelopers.api.productos.entity.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

}
