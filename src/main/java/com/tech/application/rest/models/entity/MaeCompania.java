package com.tech.application.rest.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="MAE_EMPRESAS")
public class MaeCompania {

	
	@Id
	@Column(name="COD_EMPRESA",nullable=false,unique=true,length=10)	
	private String id;
	
	@Column(name="DES_RAZON_SOCIAL",nullable=false,length=100)
	private String desrazonsocial;
	
	@Column(name="DES_NOMBRE_COMERCIAL",nullable=false,length=100)
	private String desnombrecomercial;
	
	@Column(name="DES_DIRECCION",nullable=true,length=100)
	private String desdireccion;
	
	@Column(name="NUM_RUC_EMPRESA",nullable=false,length=20)
	private String numrucempresa;

	@Column(name="TIP_DOC_REPRES",nullable=true,length=2)
	private String tipdocrepres;

	@Column(name="NUM_DOC_REPRES",nullable=true,length=15)
	private String numdocrepres;

	@Column(name="DES_REPRES_LEGAL",nullable=true,length=50)
	private String desrepreslegal;

	@Lob
	@Column(name="BMP_LOGO",nullable=true,columnDefinition = "VARBINARY(MAX)")
    private byte[] bmplogo;

	@Lob
	@Column(name="BMP_FIRMA",nullable=true)
    private byte[] bmpfirma;

	/************************************ */
	

	
}
