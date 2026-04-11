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
@Table(name="MAE_TABLA_DET")
public class MaeTablaDet {

	@Column(name="id_tabla",nullable=false)
	private Integer idTabla;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tabla_det",nullable=false)	
	private Integer id;
	
	@Column(name="codigo",nullable=false,length=5)			
	private String codigo;	
	
	@Column(name="nombre",nullable=false,length=250)			
	private String nombre;	
	
	@Column(name="valor_ini",nullable=true)			
	private Float valorIni;	
	
	@Column(name="valor_fin",nullable=true)			
	private Float valorFin;		

	@Column(name="ind_visible",nullable=true,length=2)			
	private String indVisible;

	
	public MaeTablaDet(Integer idTabla, String codigo, String nombre, Float valorIni, Float valorFin,
			String indVisible) {
		this.idTabla = idTabla;
		this.codigo = codigo;
		this.nombre = nombre;
		this.valorIni = valorIni;
		this.valorFin = valorFin;
		this.indVisible = indVisible;
	}
	
    public static MaeTablaDet dtoToEntity(MaeTablaDet detalle){
    	MaeTablaDet detalleEntity = new MaeTablaDet();
    	
    	detalleEntity.setIdTabla(detalle.getIdTabla());
    	detalleEntity.setId(detalle.getId());
    	detalleEntity.setCodigo(detalle.getCodigo());
    	detalleEntity.setNombre(detalle.getNombre());    	
    	detalleEntity.setValorIni(detalle.getValorIni());
    	detalleEntity.setValorFin(detalle.getValorFin());
    	detalleEntity.setIndVisible(detalle.getIndVisible());    	
        return detalleEntity;
    }	
	
}
