package com.tech.application.rest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tech.application.rest.models.entity.RepBoletaPago;

@Repository
public interface IRepBoletaPagoDao extends JpaRepository<RepBoletaPago, Integer>{
    
    @Query(value="{call SP_SKM_GENERA_BOLETA_PAGO(:codempresa,:ano,:mes,:version,:codpersonal,:codusuario,:docidentidad)}",nativeQuery=true)
	public List<RepBoletaPago>  execProcBoletaPago(
					@Param("codempresa") String codempresa,
					@Param("ano") String ano,
					@Param("mes") String mes,
					@Param("version") String version,
					@Param("codpersonal") String codpersonal,					
					@Param("codusuario") String codusuario,					
					@Param("docidentidad") String docidentidad										
					);

	@Query(value="{call SP_SKM_VALIDA_VISUALIZACION_DOC(:codempresa,:anoproceso,:mesproceso,:version,:codpersonal,:codusuario,:numdocidentidad,:tipdocumento)}",nativeQuery=true)
	public Integer  execProcValidaVisualizacion(
					@Param("codempresa") String codempresa,
					@Param("anoproceso") String anoproceso,					
					@Param("mesproceso") String mesproceso,
					@Param("version") String version,					
					@Param("codpersonal") String codpersonal,
					@Param("codusuario") String codusuario,
					@Param("numdocidentidad") String numdocidentidad,
					@Param("tipdocumento") String tipdocumento										
					);

}
