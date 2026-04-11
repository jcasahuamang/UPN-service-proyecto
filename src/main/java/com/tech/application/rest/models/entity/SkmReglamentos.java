package com.tech.application.rest.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "skm_reglamentos")
public class SkmReglamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable= false)
    private Integer id;

    @Column(name="titulo",nullable= false,length=250)
    private String titulo;

    @Column(name="descripcion",nullable= true,length=250)
    private String descripcion;

    @Column(name="estado",nullable= true)
    private Integer estado;

    @Column(name="ruta",nullable = true)
    private String ruta;

    @Column(name="cod_empresa",nullable = false,length=10)
    private String empresa;

    @Column(name="tipo",nullable = false,length=40)
    private String tipo;

    
    @Column(name="usu_creacion",nullable= true,length=250)
    private String usucreacion;

    @Column(name="fec_creacion",nullable= true)
    private Date feccreacion;    
    
}
