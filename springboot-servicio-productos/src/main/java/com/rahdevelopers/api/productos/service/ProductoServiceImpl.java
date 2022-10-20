package com.rahdevelopers.api.productos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rahdevelopers.api.productos.entity.ProductoEntity;
import com.rahdevelopers.api.productos.repository.ProductoRepository;

@Service
@Transactional(readOnly = true)
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Value("${server.port}")
	private Integer port;

	@Override
	public List<ProductoEntity> findAll() {
		return this.productoRepository.findAll().stream().map(producto -> {
			producto.setPort(this.port);
			return producto;
		}).collect(Collectors.toList());
	}

	@Override
	public ProductoEntity findById(Long id) {
		return this.productoRepository.findById(id).map(producto -> {
			producto.setPort(this.port);
			return producto;
		}).orElse(null);
	}

	@Override
	@Transactional
	public ProductoEntity save(ProductoEntity productoEntity) {
		return this.productoRepository.save(productoEntity);
	}

	@Override
	@Transactional
	public ProductoEntity update(ProductoEntity productoEntity, Long id) {
		ProductoEntity entity = this.productoRepository.findById(id).orElseThrow();
		entity.setNombre(productoEntity.getNombre());
		entity.setPrecio(productoEntity.getPrecio());
		return this.productoRepository.save(entity);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		this.productoRepository.deleteById(id);
	}

}
