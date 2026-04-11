package com.tech.application.rest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.tech.application.rest.models.entity.keys.PlaPersonalId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PLA_PERSONAL")
@IdClass(PlaPersonalId.class) 
public class PlaPersonal implements Serializable {

    @Id
    @Column(name="COD_EMPRESA",nullable= false,length=10)
    private String codempresa;

    @Id
    @Column(name="COD_PERSONAL",nullable= false,length=25)
    private String codpersonal;

    @Column(name="COD_TIPO_PLANILLA",nullable= true,length=2)
    private String codtipoplanilla;

    @Column(name="APE_PATERNO",nullable= true,length=50)
    private String apepaterno;

    @Column(name="NOM_TRABAJADOR",nullable= true,length=30)
    private String nomtrabajador;

    @Column(name="COD_C_COSTOS",nullable= true,length=20)
    private String codccostos;

    @Column(name="FEC_NACIMIENTO",nullable= true)
    private Date fecnacimiento;

    @Column(name="TIP_SEXO",nullable= true,length=2)
    private String tipsexo;

    @Column(name="TIP_ESTADO",nullable= true,length=2)
    private String tipestado;

    @Column(name="TIP_ESTADO_CIVIL",nullable= true,length=2)
    private String tipestadocivil;

    @Column(name="FEC_INGRESO",nullable= true,length=8)
    private Date fecingreso;

    @Column(name="COD_MONEDA_PAGO",nullable= true,length=2)
    private String codmonedapago;

    @Column(name="NUM_HIJOS",nullable= true,length=5)
    private Double numhijos;

    @Column(name="NUM_SUELDO",nullable= true,length=5)
    private Double numsueldo;

    @Column(name="COD_CATEGORIA",nullable= true,length=2)
    private String codcategoria;

    @Column(name="NUM_CUENTA_BANCO_PAGO",nullable= true,length=20)
    private String numcuentabancopago;

    @Column(name="COD_AFP",nullable= true,length=2)
    private String codafp;

    @Column(name="COD_CARGO",nullable= true,length=3)
    private String codcargo;

    @Column(name="TIP_PENSION",nullable= true,length=2)
    private String tippension;

    @Column(name="APE_MATERNO",nullable= true,length=50)
    private String apematerno;

    @Column(name="COD_UNICO_SPP",nullable= true,length=12)
    private String codunicospp;

    @Column(name="FEC_CESADO",nullable= true)
    private Date feccesado;

    @Column(name="IND_JUBILADO_AFP",nullable= true,length=1)
    private String indjubiladoafp;

    @Column(name="COD_SUCURSAL",nullable= true,length=20)
    private String codsucursal;

    @Column(name="IND_ASIG_FAMILIAR",nullable= true,length=1)
    private String indasigfamiliar;

    @Column(name="ind_ria",nullable= true,length=1)
    private String indria;

    @Column(name="SUELDO_NETO",nullable= true)
    private Double sueldoneto;

    @Column(name="IND_NETO",nullable= true,length=1)
    private String indneto;

    @Column(name="IND_COMISION_MIXTA",nullable= true,length=1)
    private String indcomisionmixta;

    @Column(name="IND_RIA_VAC",nullable= true,length=1)
    private String indriavac;

    @Column(name="IND_RIA_CTS",nullable= true,length=1)
    private String indriacts;

    @Column(name="IND_RIA_GRATI",nullable= true,length=1)
    private String indriagrati;

    @Column(name="COD_AUXILIAR_BANCO_CUENTA",nullable= true,length=6)
    private String codauxiliarbancocuenta;

}
