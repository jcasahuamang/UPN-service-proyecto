package com.tech.application.rest.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="MAE_TABLA")
public class MaeTabla {

	@Column(name="id_compania",nullable=false)
	private Integer idCompania;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tabla",nullable=false)	
	private Integer id;
	
	@Column(name="tipo_tabla",nullable=false,length=15)			
	private String tipoTabla;
	
	@Column(name="nombre",nullable=false,length=250)			
	private String nombre;
	

	@Column(name="ind_sistema",nullable=true,length=2)			
	private String indSistema;

	
	public MaeTabla(Integer idCompania, String tipoTabla, String nombre, String indSistema) {
		this.idCompania = idCompania;
		this.tipoTabla = tipoTabla;
		this.nombre = nombre;
		this.indSistema = indSistema;
	}
	
	
}
