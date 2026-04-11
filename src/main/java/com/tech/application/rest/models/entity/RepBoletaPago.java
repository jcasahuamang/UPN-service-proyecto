package com.tech.application.rest.models.entity;

import java.util.Date;

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
public class RepBoletaPago {

    @Id
    @Column
    private Integer NUM_CORREL;


    @Column
    private Date FEC_INGRESO;

    @Column
    private Date FEC_CESADO;

    @Column
    private String NUM_AUTOGEN_IPSS;
    
    @Column
    private String COD_UNICO_SPP;
    
    @Column
    private String c_des_tipo_planilla;
    
    @Column
    private String IND_TAREO;
    
    @Column
    private Double NUM_DIAS;
    
    @Column
    private Integer DIA_INGRESO;
    
    @Column
    private Double DIAS_FIN;
    
    @Column
    private String COD_PERSONAL;
    
    @Column
    private Double NRO_HORAS;
    
    @Column
    private Double NRO_HORAS_EXTRAS;
    
    @Column
    private String COD_EMPRESA;
    
    @Column
    private String COD_TIPO_PLANILLA;
    
    
    @Column
    private String c_nom_personal;
    
    @Column
    private String NUM_SUCURSAL;
    
    @Column
    private String DES_BANCO;
    
    @Column
    private String DES_C_COSTOS;
    
    @Column
    private String NUM_CUENTA_BANCO_PAGO;
    
    @Column
    private String NUM_REG_PATRONAL;
    
    @Column
    private String NUM_RUC_EMPRESA;
    
    @Column
    private String NUM_DECRET_SUPRE;
    
    @Column
    private String DES_NOMBRE_COMERCIAL;
    
    @Column
    private String DES_DIRECCION;
    
    @Column
    private String COD_USER_ACTUAL;
    
    @Column
    private String NUM_DOC_IDENTIDAD;
    
    @Column
    private String c_tpo_pago;
    
    @Column
    private String c_nom_afp;
    
    @Column
    private Double c_sueldo;
    
    @Column
    private Date  c_fec_inicio_vac;
    
    @Column
    private Date c_fec_fin_vac;
    
    @Column
    private Double c_num_dias_vac;
    
    @Column
    private String c_des_cargo;
    
    @Column
    private String IN_DESC;
    
    @Column
    private Double IN_IMP;
    
    @Column
    private String DE_DESC;
    
    @Column
    private Double DE_IMP;
    
    @Column
    private String AP_DESC;
    
    @Column
    private Double AP_IMP;
    
    @Column
    private Double IN_CANTI;
    
    @Column
    private String IN_CANTI_DES;
    
    @Column
    private String c_tip_proc_sema;
    
    @Column
    private Double c_nro_horas_dom;
    
    @Column
    private Double c_dias_dom;
    
    @Column
    private String c_des_representante;
    
    @Column
    private String c_des_repres_empresa;
    
    @Column
    private String c_des_razon_social;
    
    @Column
    private String c_tip_cargo;
    
    @Column
    private String c_des_vales;
    
    @Column
    private String cod_concepto;
    
    @Column
    private String ind_hextras_conc;
    
    @Column
    private String c_des_sueldo_categ;
    
    @Column
    private String c_tip_doc_identidad;
    
    @Column
    private String MENSAJE;
    
    @Column
    private String bmp_firma_ruta;
    
    @Column
    private String bmp_logo_ruta;
    
    @Column
    private String per_lab;
    
    @Column
    private Double IMP_TIP_CAMBIO;
    
    @Column
    private String DES_MONEDA;
    
    @Column
    private String IND_NETO;
    
    @Column
    private String fec_ins_afp;
    
    @Column
    private String tip_cargo;
    
    @Column
    private String tip_sit_laboral;
    
    @Column
    private String TIP_DISCAPACIDAD;
    
    @Column
    private String NUM_BARRA;
    
    @Column
    private String REM;
    
    @Column
    private String cod_c_costos;
    
    @Column
    private Double c_hextras;
    
    @Column
    private Double c_dsubsidio;
    
    @Column
    private String c_tip_pension;
    
    @Column
    private String SIS_SALUD;
    
    @Column
    private Double c_dnsub;
    
    @Column
    private String ASEG_VLEY;
    
    @Column
    private String ASEG_EPS;
    
    @Column
    private Double c_dfalta;
    
    @Column
    private Double c_dlsg;
    
    @Column
    private Double c_dssg;
    
    @Column
    private String TIP_SEXO;
    
    @Column
    private String NACIONALIDAD;
    
    @Column
    private String des_area;
    
    @Column
    private Double c_hextras_25;
    
    @Column
    private Double c_hextras_35;
    
    @Column
    private Double c_hextras_100;
    
    @Column
    private Double c_tardanza;
    
    @Column
    private String des_regimen;
    
    @Column
    private String c_num_doc_repres_empresa;
    
    @Column
    private Date ause_fecini1;
    
    @Column
    private Date ause_fecfin1;
    
    @Column
    private String ause_tipo1;
    
    @Column
    private Date ause_fecini2;
    
    @Column
    private Date ause_fecfin2;
    
    @Column
    private String ause_tipo2;
    
    @Column
    private Date ause_fecini3;
    
    @Column
    private Date ause_fecfin3;
    
    @Column
    private String ause_tipo3;
    
    @Column
    private Date ause_fecini4;
    
    @Column
    private Date ause_fecfin4;
    
    @Column
    private String ause_tipo4;
    
    @Column
    private Date ause_fecini5;
    
    @Column
    private Date ause_fecfin5;
    
    @Column
    private String ause_tipo5;
    
    @Column
    private String tip_ingreso_tard;

    @Column
    private Double neto_sol;

    @Column
    private Double neto_dolar;
    
    
}
