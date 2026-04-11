package com.tech.application.rest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tech.application.rest.models.entity.SkmAprobaciones;
import com.tech.application.rest.models.entity.ValidacionRespuesta;

@Repository
public interface ISkmAprobacionesDao extends JpaRepository<SkmAprobaciones, Long> {

    @Query(nativeQuery = true, 
        value= "select id,llave,tipo_aprobacion,accion,usuario,cod_empresa,cod_personal,id_solicitud from skm_aprobaciones where id = :id")
    public SkmAprobaciones BuscarById(@Param("id") Long id);

    @Query(nativeQuery = true, 
        value= "select id,llave,tipo_aprobacion,accion,usuario,cod_empresa,cod_personal,id_solicitud from skm_aprobaciones")
    public List<SkmAprobaciones> BuscarAll();   
    
    @Query(value="{call sp_skm_valida_aprobaciones(:llave,:tipoaprobacion,:accion,:usuario)}",nativeQuery=true)
	public List<ValidacionRespuesta>  execProcValidaAprobaciones(
					@Param("llave") String llave,
					@Param("tipoaprobacion") String tipoaprobacion,                    
					@Param("accion") String accion,
					@Param("usuario") String usuario
					);

    @Query(value="{call sp_skm_ejecuta_aprobaciones(:llave,:tipoaprobacion,:accion,:usuario)}",nativeQuery=true)
	public Integer  execProcEjecutaAprobaciones(
					@Param("llave") String llave,
					@Param("tipoaprobacion") String tipoaprobacion,                    
					@Param("accion") String accion,
					@Param("usuario") String usuario										
					);                    
}
