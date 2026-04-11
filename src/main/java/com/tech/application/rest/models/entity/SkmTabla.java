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
@Table(name = "skm_tabla")
public class SkmTabla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable= false)
    private Long id;

    @Column(name="cod_empresa", nullable= false, length=10)
    private String empresa;

    @Column(name="tipo", nullable= false, length=40)
    private String tipo;

    @Column(name="codigo", nullable= false, length=40)
    private String codigo;

    @Column(name="descripcion", nullable= true, length=250)
    private String descripcion;

    
}