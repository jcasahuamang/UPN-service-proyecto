package com.tech.application.rest.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.tech.application.rest.models.entity.keys.SkmConfigVisualizacionId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SKM_CONFIG_VISUALIZACION")
@IdClass(SkmConfigVisualizacionId.class) 
public class SkmConfigVisualizacion {
    
    @Id
    @Column(name="COD_EMPRESA",nullable= false,length=10)
    private String codempresa;

    @Id
    @Column(name="ANO_PROCESO",nullable= false,length=4)
    private String anoproceso;

    @Id
    @Column(name="MES_PROCESO",nullable= false,length=2)
    private String mesproceso;

    @Id
    @Column(name="COD_TIPO_PLANILLA",nullable= false,length=2)
    private String codtipoplanilla;

    @Id
    @Column(name="TIP_DOCUMENTO",nullable= false,length=12)
    private String tipdocumento;

    @Column(name="FECHA",nullable= true)
    private Date fecha;    

}
