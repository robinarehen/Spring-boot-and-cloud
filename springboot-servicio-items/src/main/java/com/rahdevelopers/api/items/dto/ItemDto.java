package com.rahdevelopers.api.items.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

	private ProductoDto productoDto;
	private Integer cantidad;

	public Double getTotal() {
		return (this.getProductoDto().getPrecio() * this.cantidad.doubleValue());
	}
}
