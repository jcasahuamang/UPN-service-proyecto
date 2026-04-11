package com.tech.application.rest.models.entity;

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
@Table(name = "skm_aprobaciones")
public class SkmAprobaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable= false)
    private Long id;

    
    @Column(name="llave",nullable= false,length=250)
    private String llave; 

    @Column(name="tipo_aprobacion",nullable= false,length=100)
    private String tipoaprobacion; 

    @Column(name="accion",nullable= false,length=100)
    private String accion; 

    @Column(name="usuario",nullable= false,length=100)
    private String usuario; 

    @Column(name="cod_empresa",nullable= true,length=100)
    private String empresa; 

    @Column(name="cod_personal",nullable= true,length=100)
    private String personal; 


    @Column(name="id_solicitud",nullable= false)
    private Long idsolicitud;

}
