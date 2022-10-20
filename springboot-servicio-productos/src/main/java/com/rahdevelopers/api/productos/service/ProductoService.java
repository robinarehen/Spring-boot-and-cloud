package com.rahdevelopers.api.productos.service;

import java.util.List;

import com.rahdevelopers.api.productos.entity.ProductoEntity;

public interface ProductoService {

	public List<ProductoEntity> findAll();
	
	public ProductoEntity findById(Long id);
	
	public ProductoEntity save(ProductoEntity productoEntity);
	
	public ProductoEntity update(ProductoEntity productoEntity, Long id);
	
	public void deleteById(Long id);
}
