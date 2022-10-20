package com.rahdevelopers.api.items.apicontroller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rahdevelopers.api.items.dto.ItemDto;
import com.rahdevelopers.api.items.dto.ProductoDto;
import com.rahdevelopers.api.items.service.ItemService;

@RefreshScope
@RestController
public class ItemApiController {

	@Autowired
	@Qualifier("serviceImplFeign")
	private ItemService itemService;

	@Autowired
	private Environment environment;

	@GetMapping("/config-server")
	public ResponseEntity<Map<String, String>> configServer(@Value("${configuracion.text}") String configText,
			@Value("${server.port}") String port) {

		Map<String, String> responseJson = new HashMap<>();
		responseJson.put("configText", configText);
		responseJson.put("port", port);

		List<String> profiles = Arrays.asList(this.environment.getActiveProfiles());

		profiles.stream().filter(filter -> filter.equals("dev")).forEach(profile -> {
			responseJson.put("autor.name", this.environment.getProperty("configuracion.autor.name"));
			responseJson.put("autor.email", this.environment.getProperty("configuracion.autor.email"));
		});

		return ResponseEntity.ok(responseJson);
	}

	@HystrixCommand(fallbackMethod = "getLitarItemsError")
	@GetMapping("/listar")
	public List<ItemDto> getLitarItems() {
		return this.itemService.findAll();
	}

	@HystrixCommand(fallbackMethod = "getItemError")
	@GetMapping("/{id}/cantidad/{cantidad}")
	public ItemDto getItem(@PathVariable Long id, @PathVariable Integer cantidad) {
		return this.itemService.findById(id, cantidad);
	}

	@PostMapping
	public ProductoDto postProducto(@RequestBody ProductoDto productoDto) {
		return this.itemService.save(productoDto);
	}

	@PutMapping("/{id}")
	public ProductoDto putProducto(@RequestBody ProductoDto productoDto, @PathVariable Long id) {
		return this.itemService.update(productoDto, id);
	}

	@DeleteMapping("/{id}")
	public void deleteProducto(@PathVariable Long id) {
		this.itemService.delete(id);
	}

	public ItemDto getItemError(Long id, Integer cantidad) {
		ProductoDto productoDto = new ProductoDto();
		productoDto.setID(id);
		productoDto.setNombre("Error Inesperado");
		productoDto.setPrecio(0L);
		productoDto.setFechaCreacion(new Date());
		productoDto.setPort(0);

		return new ItemDto(productoDto, cantidad);
	}

	public List<ItemDto> getLitarItemsError() {

		ProductoDto productoDto = new ProductoDto();

		productoDto.setID(0L);
		productoDto.setNombre("Error Inesperado");
		productoDto.setPrecio(0L);
		productoDto.setFechaCreacion(new Date());
		productoDto.setPort(0);

		List<ItemDto> listItemDtos = new ArrayList<>();
		listItemDtos.add(new ItemDto(productoDto, 0));

		return listItemDtos;
	}
}
