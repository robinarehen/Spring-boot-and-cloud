package com.rahdevelopers.api.items.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rahdevelopers.api.items.dto.ItemDto;
import com.rahdevelopers.api.items.dto.ProductoDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("serviceImplRest")
public class ItemServiceImplRest implements ItemService {

	@Autowired
	private RestTemplate restTemplate;
	private final String PATH_URL = "http://servicio-productos";

	@Override
	public List<ItemDto> findAll() {
		log.info("ItemServiceImplRest.findAll");
		String url = String.format("%s/listar", this.PATH_URL);
		List<ProductoDto> productoDtos = Arrays.asList(this.restTemplate.getForObject(url, ProductoDto[].class));
		return productoDtos.stream().map(producto -> new ItemDto(producto, 1)).collect(Collectors.toList());
	}

	@Override
	public ItemDto findById(Long id, Integer cantidad) {
		log.info("ItemServiceImplRest.findById");
		String url = String.format("%s/{id}", this.PATH_URL);
		ProductoDto productoDto = this.restTemplate.getForObject(url, ProductoDto.class, this.pathVariables(id));
		return new ItemDto(productoDto, cantidad);
	}

	@Override
	public ProductoDto save(ProductoDto productoDto) {
		log.info("ItemServiceImplRest.save");
		HttpEntity<ProductoDto> requestEntity = new HttpEntity<>(productoDto);
		ResponseEntity<ProductoDto> responseEntity = this.restTemplate.exchange(this.PATH_URL, HttpMethod.POST,
				requestEntity, ProductoDto.class);
		return responseEntity.getBody();
	}

	@Override
	public ProductoDto update(ProductoDto productoDto, Long id) {
		log.info("ItemServiceImplRest.update");
		HttpEntity<ProductoDto> requestEntity = new HttpEntity<>(productoDto);
		String url = String.format("%s/{id}", this.PATH_URL);
		ResponseEntity<ProductoDto> responseEntity = this.restTemplate.exchange(url, HttpMethod.PUT, requestEntity,
				ProductoDto.class, this.pathVariables(id));
		return responseEntity.getBody();
	}

	@Override
	public void delete(Long id) {
		log.info("ItemServiceImplRest.delete");
		String url = String.format("%s/{id}", this.PATH_URL);
		this.restTemplate.delete(url, this.pathVariables(id));
	}

	private Map<String, String> pathVariables(Long id) {
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		return pathVariables;
	}

}
