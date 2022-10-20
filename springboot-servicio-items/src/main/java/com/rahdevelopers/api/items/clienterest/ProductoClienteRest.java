package com.rahdevelopers.api.items.clienterest;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rahdevelopers.api.items.dto.ProductoDto;

@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {

	@GetMapping("/listar")
	public List<ProductoDto> getListarProductos();

	@GetMapping("/{id}")
	public ProductoDto getProducto(@PathVariable Long id);

	@PostMapping
	public ProductoDto postProducto(@RequestBody ProductoDto productoDto);

	@PutMapping("/{id}")
	public ProductoDto putProducto(@RequestBody ProductoDto productoDto, @PathVariable Long id);
	
	@DeleteMapping("/{id}")
	public void deleteProducto(@PathVariable Long id);
}
