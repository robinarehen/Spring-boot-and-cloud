package com.rahdevelopers.api.items.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDto {

	private Long ID;
	private String nombre;
	private Long precio;
	private Date fechaCreacion;
	private Integer port;
}
