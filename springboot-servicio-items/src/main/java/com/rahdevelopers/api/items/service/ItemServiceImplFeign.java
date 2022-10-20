package com.rahdevelopers.api.items.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahdevelopers.api.items.clienterest.ProductoClienteRest;
import com.rahdevelopers.api.items.dto.ItemDto;
import com.rahdevelopers.api.items.dto.ProductoDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("serviceImplFeign")
public class ItemServiceImplFeign implements ItemService {

	@Autowired
	private ProductoClienteRest clienteRest;

	@Override
	public List<ItemDto> findAll() {
		log.info("ItemServiceImplFeign.findAll");
		return this.clienteRest.getListarProductos().stream().map(producto -> new ItemDto(producto, 1))
				.collect(Collectors.toList());
	}

	@Override
	public ItemDto findById(Long id, Integer cantidad) {
		return new ItemDto(this.clienteRest.getProducto(id), cantidad);
	}

	@Override
	public ProductoDto save(ProductoDto productoDto) {
		return this.clienteRest.postProducto(productoDto);
	}

	@Override
	public ProductoDto update(ProductoDto productoDto, Long id) {
		return this.clienteRest.putProducto(productoDto, id);
	}

	@Override
	public void delete(Long id) {
		this.clienteRest.deleteProducto(id);
	}

}
