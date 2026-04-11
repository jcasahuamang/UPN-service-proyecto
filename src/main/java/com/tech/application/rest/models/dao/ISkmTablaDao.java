package com.tech.application.rest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tech.application.rest.models.entity.SkmTabla;

@Repository
public interface ISkmTablaDao extends JpaRepository<SkmTabla, Long> {

    @Query(nativeQuery = true, 
        value= "select id,cod_empresa,tipo,codigo,descripcion from skm_tabla where id = :id")
    public SkmTabla BuscarById(@Param("id") Long id);

    @Query(nativeQuery = true, 
        value= "select id,cod_empresa,tipo,codigo,descripcion from skm_tabla")
    public List<SkmTabla> BuscarAll();

    @Query(nativeQuery = true, 
        value= "select id,cod_empresa,tipo,codigo,descripcion from skm_tabla "
         +" where cod_empresa = :empresa")
    public List<SkmTabla> BuscarByEmpresa(@Param("empresa") String empresa);

    @Query(nativeQuery = true, 
        value= "select id,cod_empresa,tipo,codigo,descripcion from skm_tabla "
         +" where cod_empresa = :empresa and tipo = :tabla")
    public List<SkmTabla> BuscarByEmpresaTabla(
        @Param("empresa") String empresa,
        @Param("tabla") String tabla);
}