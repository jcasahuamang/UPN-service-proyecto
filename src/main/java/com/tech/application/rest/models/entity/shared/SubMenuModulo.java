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
public class SubMenuModulo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_funcion;
	
	private Integer id_modulo;
	private Integer id_funcion_sup;
	private Integer num_nivel;
	private String des_funcion;
	private String des_url;
	private String des_icono;
	private String ind_detalle;
	private Integer ind_baja;
	private Integer nro_detalle;
	
}
