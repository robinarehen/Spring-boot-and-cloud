package com.rahdevelopers.api.items.service;

import java.util.List;

import com.rahdevelopers.api.items.dto.ItemDto;
import com.rahdevelopers.api.items.dto.ProductoDto;

public interface ItemService {

	public List<ItemDto> findAll();
	
	public ItemDto findById(Long id, Integer cantidad);
	
	public ProductoDto save(ProductoDto productoDto);
	
	public ProductoDto update(ProductoDto productoDto, Long id);
	
	public void delete(Long id);
}
