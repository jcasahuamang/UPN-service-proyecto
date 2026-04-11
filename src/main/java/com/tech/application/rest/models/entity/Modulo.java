package com.tech.application.rest.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="SIS_MODULO")
public class Modulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_modulo",nullable=false)	
	private Integer id;
	
	@Column(name="cod_modulo",nullable=false,unique=true,length=10)
	private String codigo;
	
	@Column(name="nombre",nullable=false,unique=false,length=100)
	private String nombre;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Modulo() {
		
	}

	public Modulo(@NotBlank String codigo, @NotBlank String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	
	
	
}
