package com.tech.application.rest.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "skm_anuncios")
public class SkmAnuncios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable= false)
    private Integer id;

    @Column(name="titulo",nullable= true,length=250)
    private String titulo;

    @Column(name="descripcion",nullable= true,length=250)
    private String descripcion;

    @Column(name="estado",nullable= true)
    private Integer estado;

    @Column(name="alcance",nullable= true)
    private Integer alcance;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fec_inicio_vigencia",nullable= true)
    private Date feciniciovigencia;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fec_fin_vigencia",nullable= true)
    private Date fecfinvigencia;

    @Column(name="contenido",nullable= true)
    private String contenido;

    @Column(name="cod_empresa",nullable = true)
    private String empresa;


    @Column(name="usu_creacion",nullable= true,length=250)
    private String usucreacion;

    @Column(name="fec_creacion",nullable= true)
    private Date feccreacion;    
    
}
