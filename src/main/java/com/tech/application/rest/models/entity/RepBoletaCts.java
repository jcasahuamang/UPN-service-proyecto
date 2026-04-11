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
public class RepBoletaCts {
    
    @Id
    @Column
    private Integer contar;

    @Column
    private String nom_trabajador;
    
    @Column
    private String num_cuenta_banco_cts;
    
    @Column
    private Date fec_pago;
    
    @Column
    private Date fec_inicio;
    
    @Column
    private Date fec_fin;
    
    @Column
    private Double tip_cambio_cts;
    
    @Column
    private Double imp_deposito_cts;
    
    @Column
    private Double imp_mone_secu;
    
    @Column
    private String cod_concepto;
    
    @Column
    private String nom_banco;
    
    @Column
    private String nom_concepto;
    
    @Column
    private Double imp_monto;
    
    @Column
    private String rep_legal;
    
    @Column
    private String des_empresa;
    
    @Column
    private String des_direccion;
    
    @Column
    private Integer dias_mes;
    
    @Column
    private String num_doc_identidad;
    
    @Column
    private String cod_personal;
    
    @Column
    private String abv_moneda;
    
    @Column
    private Date fec_ingreso;
    
    @Column
    private String des_cargo;
    
    @Column
    private String letra_deposi;
    
    @Column
    private String cargo_rep;
    
    @Column
    private Double imp_calculo_soles;
    
    @Column
    private Double imp_calculo_dolares;
    
    @Column
    private Double imp_interes;
    
    @Column
    private String cod_moneda_cts;
    
    @Column
    private Double num_dias_intereses;
    
    @Column
    private String firmaruta;
    
    @Column
    private String logoruta;
    
    @Column
    private String num_ruc_empresa;
    
    @Column
    private Integer dias_serv;
    
    @Column
    private Integer d_serv;
    
    @Column
    private Integer m_serv;
    
    @Column
    private Integer a_serv;
    
    @Column
    private String des_c_costos;
    
    @Column
    private String nom_sucursal;
    
    @Column
    private Double imp_retencion;
    
    @Column
    private Double imp_retencion_secu;
    
    @Column
    private Double imp_deposito_cts_total;
    
    @Column
    private Double imp_mone_secu_total;
    
    @Column
    private String ind_mype;
    
    @Column
    private String tip_doc_identidad;

    @Column
    private Integer num_meses_trab;
    
    @Column
    private Integer num_dias_trab;
    
    @Column
    private Double imp_deposito_cts_dias;
    
    @Column
    private Double imp_deposito_cts_mes;
    
    @Column
    private String des_periodo;

}
