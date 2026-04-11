package com.tech.application.rest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tech.application.rest.models.entity.SkmReglamentos;

@Repository
public interface ISkmReglamentosDao extends JpaRepository<SkmReglamentos, Integer> {
    
    	@Query(nativeQuery = true, 
			value= "select id,titulo,descripcion,estado,ruta,cod_empresa,tipo,usu_creacion,fec_creacion from skm_reglamentos where id  = :id")
    	public SkmReglamentos BuscarById(@Param("id") Integer id);

       	@Query(nativeQuery = true, 
        value= "select id,titulo,descripcion,estado,ruta,cod_empresa,tipo,usu_creacion,fec_creacion from skm_reglamentos")
        public List<SkmReglamentos> BuscarAll();

        @Query(nativeQuery = true, 
        value= "select id,titulo,descripcion,estado,ruta,cod_empresa,tipo,usu_creacion,fec_creacion from skm_reglamentos "
         +" where len(coalesce(cod_empresa,''))=0 or   cod_empresa = :empresa")
        public List<SkmReglamentos> BuscarByEmpresa(@Param("empresa") String empresa);


    
}
