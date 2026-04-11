package com.tech.application.rest.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class RepDatosPersonal {
    
    @Column
    private String codempresa;

    @Id
    @Column
    private String codpersonal;

    @Column
    private String desplanilla;

    @Column
    private String apepaterno;

    @Column
    private String apematerno;
    
    @Column
    private String nomtrabajador;

    @Column
    private String fecingreso;

    @Column
    private String feccesado;
    
    @Column
    private String tipestado;

    @Column
    private String tipsexo;
    
    @Column
    private String estadocivil;
    
    @Column
    private String fecnacimiento;

    @Column
    private String asigfamiliar;

    @Column
    private Integer numhijos;

    @Column
    private String descategoria;

    @Column
    private String descargo;

    @Column
    private String nomsucursal;

    @Column
    private String desccostos;

    @Column
    private String tippension;

    @Column
    private String nomafp;

    @Column
    private String codunicospp;

    @Column
    private String commixta;

    @Column
    private String jubilado;

    @Column
    private String bancocuenta;

    @Column
    private String numcuentapago;

    @Column
    private String desmoneda;

    @Column
    private String neto;

    @Column
    private Double sueldo;

    @Column
    private String riabasico;

    @Column
    private String riavacaciones;

    @Column
    private String riacts;

    @Column
    private String riagratificacion;

    @Column
    private String tipdocidentidad;

    @Column
    private String numdocidentidad;

}
