package com.rahdevelopers.api.usuarios.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class UsuarioEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7833724110756144125L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@Column(unique = true, nullable = false, length = 20)
	private String userName;

	@Column(length = 100, nullable = false)
	private String userPassword;

	private Boolean enabled;

	@Column(nullable = false, length = 50)
	private String nombre;

	@Column(nullable = false, length = 100)
	private String apellidos;

	@Column(unique = true, nullable = false, length = 100)
	private String email;

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate birthDate;

	private LocalDateTime createDate;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuarios_roles",
		joinColumns = @JoinColumn(name = "usuario_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"),
		uniqueConstraints = { @UniqueConstraint(columnNames = { "usuario_id", "role_id" }) }
	)
	private List<RoleEntity> roles;

	@PrePersist
	public void prePersist() {
		this.createDate = LocalDateTime.now();
		this.userPassword = this.passwordEncoder().encode(this.userPassword);
		if (this.enabled == null) {
			this.enabled = true;
		}
	}
	
	@PreUpdate
	public void preUpdate() {
		this.userPassword = this.passwordEncoder().encode(this.userPassword);
		if(this.enabled == null) {
			this.enabled = false;
		}
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
