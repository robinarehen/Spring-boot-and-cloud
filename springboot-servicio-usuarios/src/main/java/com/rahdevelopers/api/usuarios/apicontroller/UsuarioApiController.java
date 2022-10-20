package com.rahdevelopers.api.usuarios.apicontroller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rahdevelopers.api.usuarios.dto.UsuarioDto;
import com.rahdevelopers.api.usuarios.service.UsuarioService;

@RestController
public class UsuarioApiController {
	
	@Autowired
	private UsuarioService service;

	@GetMapping("/findbyusername")
	public ResponseEntity<UsuarioDto> getByUserName(@RequestParam("username") String userName) {
		return ResponseEntity.ok(this.service.findByUserName(userName));
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDto>> getAll() {
		return ResponseEntity.ok(this.service.getAll());
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDto> create(@RequestBody UsuarioDto usuarioDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(usuarioDto));
	}

	@PutMapping
	public ResponseEntity<UsuarioDto> update(@RequestBody UsuarioDto usuarioDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.update(usuarioDto));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		this.service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
