package com.rahdevelopers.api.usuarios.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rahdevelopers.api.usuarios.dto.UsuarioDto;
import com.rahdevelopers.api.usuarios.entity.UsuarioEntity;
import com.rahdevelopers.api.usuarios.repository.UsuarioRepository;
import com.rahdevelopers.api.usuarios.util.ObjectMapperUtil;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository usuarioRepository;
	private ObjectMapperUtil mapperUtil;

	@Value("${minimum-age}")
	private Integer minimumAge;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, ObjectMapperUtil mapperUtil) {
		super();
		this.usuarioRepository = usuarioRepository;
		this.mapperUtil = mapperUtil;
	}

	@Override
	public UsuarioDto findByUserName(String userName) {
		return this.usuarioRepository.findByUserName(userName).map(entity -> {
			return this.mapperUtil.entityToDto(UsuarioDto.class, entity);
		}).orElseThrow(() -> new IllegalArgumentException("Usuario Invalido"));
	}

	@Override
	public List<UsuarioDto> getAll() {
		// TODO Auto-generated method stub
		return this.usuarioRepository.findAll().stream().filter(value -> value.getEnabled()).map(entity -> {
			return this.mapperUtil.entityToDto(UsuarioDto.class, entity);
		}).collect(Collectors.toList());
	}

	@Override
	public UsuarioDto create(UsuarioDto usuarioDto) {
		// TODO Auto-generated method stub
		if (this.usuarioRepository.findByUserName(usuarioDto.getUserName()).isPresent()) {
			throw new IllegalArgumentException("El usuario ya existe.");
		}

		if (!this.isValidBirthDate(usuarioDto)) {
			throw new IllegalArgumentException("El usuario debe ser mayor de edad.");
		}

		UsuarioEntity usuarioEntity = this.mapperUtil.dtoToEntity(UsuarioEntity.class, usuarioDto);

		return this.mapperUtil.entityToDto(UsuarioDto.class, this.usuarioRepository.save(usuarioEntity));
	}

	@Override
	public UsuarioDto update(UsuarioDto usuarioDto) {
		// TODO Auto-generated method stub
		LocalDateTime createDate = this.usuarioRepository.findById(usuarioDto.getID())
				.orElseThrow(() -> new IllegalArgumentException("Id Invalido")).getCreateDate();

		if (!this.isValidBirthDate(usuarioDto)) {
			throw new IllegalArgumentException("El usuario debe ser mayor de edad.");
		}

		UsuarioEntity usuarioEntity = this.mapperUtil.dtoToEntity(UsuarioEntity.class, usuarioDto);

		usuarioEntity.setCreateDate(createDate);

		return this.mapperUtil.entityToDto(UsuarioDto.class, this.usuarioRepository.save(usuarioEntity));
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		UsuarioEntity entity = this.usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id Invalido"));
		entity.setEnabled(false);
		this.usuarioRepository.save(entity);
	}

	/**
	 * The age must be greater than fourteen years
	 * 
	 * @return
	 */
	private Boolean isValidBirthDate(UsuarioDto usuarioDto) {
		LocalDate currentDate = LocalDate.now();

		Period period = Period.between(usuarioDto.getBirthDate(), currentDate);

		if (period.getYears() < this.minimumAge) {
			return false;
		}

		return true;
	}

}
