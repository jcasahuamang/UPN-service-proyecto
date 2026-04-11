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
public class RepCertificado5ta {
    
    @Id
    @Column
    private Integer num_id;

    @Column  
    private String cod_empresa;

    @Column
    private String cod_personal;

    @Column
    private String cod_tipo_planilla;
    
    @Column
    private String ano_proceso;
    
    @Column
    private Integer c_ano_proceso;
    
    @Column
    private Double imp_tot_percibido;
    
    @Column
    private Double num_uit_deducible;
    
    @Column
    private Double c_imp_impuesto_anual;
    
    @Column
    private String c_des_personal;
    
    @Column
    private String c_sexo_personal;
    
    @Column
    private Double c_retencion_cia;
    
    @Column
    private Double c_retencion_cia_actual;
    
    @Column
    private Double c_sum_remunera_ma;
    
    @Column
    private Double c_sum_imp_retencion_ma;
    
    @Column
    private Double c_sum_imp_retencion;
    
    @Column
    private Double c_imp_valor_iut;
    
    @Column
    private String c_des_empresa;
    
    @Column
    private String c_ruc_empresa;
    
    @Column
    private String c_des_direccion;

    @Column
    private Double c_imp_percibido_liq;

    @Column
    private Double c_imp_dev_retencion;

    @Column
    private Double c_imp_retencion_liq;

    @Column
    private Integer c_count_qta_liq;
    
    @Column
    private String c_nom_sucursal;
    
    @Column
    private String c_num_doc_identidad;
    
    @Column
    private Date fec_ingreso;
    
    @Column
    private String c_des_cargo;
    
    @Column
    private Double tope_1;
    
    @Column
    private Double tope_3;
    
    @Column
    private Double tope_4;
    
    @Column
    private Double tope_2;
    
    @Column
    private String tope_1_letras;
    
    @Column
    private String tope_3_letras;
    
    @Column
    private String tope_4_letras;
    
    @Column
    private String tope_1_porc;
    
    @Column
    private String tope_3_porc;
    
    @Column
    private String tope_4_porc;
    
    @Column
    private String tope_2_letras;
    
    @Column
    private String tope_2_porc;
    
    @Column
    private String tope_5_letras;
    
    @Column
    private String tope_5_porc;
    
    @Column
    private Double c_monto_1;
    
    @Column
    private Double c_monto_2;
    
    @Column
    private Double c_monto_3;
    
    @Column
    private Double c_monto_4;
    
    @Column
    private Double c_porc_1;
    
    @Column
    private Double c_porc_3;
    
    @Column
    private Double c_porc_4;
    
    @Column
    private Double c_porc_2;
    
    @Column
    private Double c_porc_5;
    
    @Column
    private Integer ano_ingreso;
    
    @Column
    private String mes_ingreso;
    
    @Column
    private String dia_ingreso;
    
    @Column
    private Double c_imp_resultado;
    
    @Column
    private Double c_imp_resul_liq;
    
    @Column
    private String cod_concepto;
    
    @Column
    private String c_dir_personal;
    
    @Column
    private String c_des_concepto;
    
    @Column
    private String c_des_rep_legal;
    
    @Column
    private String c_num_rep_legal;
    
    @Column
    private String c_des_distrito;
    
    @Column
    private Integer ano_cesado;
    
    @Column
    private String mes_cesado;
    
    @Column
    private String dia_cesado;
    
    @Column
    private String cod_provincia;
    
    @Column
    private Double retencion_cia_emp;
    
    @Column
    private Double tot_reten_cia_emp;
    
    @Column
    private String c_tip_doc_identidad;
    
    @Column
    private String jefr_hum;
    
    @Column
    private String cargo_repr;
    
    @Column
    private String origen;
    
    @Column
    private Double c_tot_imp_retenido_otras;
    
    @Column
    private String c_administrador_obra;
    
    @Column
    private String c_tip_doc_admobra;
    
    @Column
    private String c_num_doc_admobra;
    
    @Column
    private String logo_ruta;
    
    @Column
    private String firma_ruta;
    
    @Column
    private Integer nodomiciliado;
    
    @Column
    private Integer grupo;
    
    @Column
    private Double retenido_total_importe;
    
    @Column
    private String retenido_total_formato;
    
    @Column
    private String fecha_impresion;    

}
