package com.tech.application.rest.models.entity.shared;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class MenuModulo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_modulo;
	private String des_grupo_menu;
	private String des_grupo;
	private String des_icono;
	
}