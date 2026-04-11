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
@Table(name = "skm_prestamos")
public class SkmPrestamos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable= false)
    private Long id;

    
    @Column(name="cod_empresa",nullable= false,length=10)
    private String empresa;

    @Column(name="cod_personal",nullable= false,length=25)
    private String codpersonal;

    @Column(name="tipo",nullable= false,length=40)
    private String tipo;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fec_solicitud",nullable= false)
    private Date fecsolicitud;

    @Column(name="moneda",nullable= true,length=40)
    private String moneda;
    
    @Column(name="importe",nullable= true)
    private Double importe;


    @Column(name="nro_cuotas",nullable= true)
    private Integer cuotas;

    @Column(name="observacion",nullable= true,length=250)
    private String observacion;


    @Column(name="estado",nullable= false,length=6)
    private String estado;

    
    @Column(name="usu_creacion",nullable= true,length=250)
    private String usucreacion;

    @Column(name="fec_creacion",nullable= true)
    private Date feccreacion;    

    @Column(name="codigo_transferencia",nullable= true,length=90)
    private String codigotransferencia;
}
