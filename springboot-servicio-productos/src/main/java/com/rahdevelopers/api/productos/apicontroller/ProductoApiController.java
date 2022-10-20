package com.rahdevelopers.api.productos.apicontroller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rahdevelopers.api.productos.entity.ProductoEntity;
import com.rahdevelopers.api.productos.service.ProductoService;

@RestController
public class ProductoApiController {

	@Autowired
	private ProductoService productoService;

	@GetMapping("/listar")
	public List<ProductoEntity> getListarProductos() {
		return this.productoService.findAll();
	}

	@GetMapping("/{id}")
	public ProductoEntity getProducto(@PathVariable Long id) {
		return this.productoService.findById(id);
	}

	@PostMapping
	public ResponseEntity<ProductoEntity> postProducto(@RequestBody ProductoEntity productoEntity) {
		ProductoEntity entity = this.productoService.save(productoEntity);
		String url = String.format("/api/producto/%s", entity.getID());
		//return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(URI.create(url)).body(entity);
		return ResponseEntity.created(URI.create(url)).body(entity);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductoEntity> putProducto(@RequestBody ProductoEntity productoEntity,
			@PathVariable Long id) {
		return new ResponseEntity<>(this.productoService.update(productoEntity, id), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public void deleteProducto(@PathVariable Long id) {
		this.productoService.deleteById(id);
	}
}
