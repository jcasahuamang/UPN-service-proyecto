package com.tech.application.rest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tech.application.rest.models.entity.RepCertificado5ta;

@Repository
public interface IRepCertificado5taDao extends JpaRepository<RepCertificado5ta, Integer>{

    
    @Query(value="{call SP_SKM_GENERA_CERTIFICA_5TA(:codempresa,:ano,:mes,:codpersonal,:codusuario,:docidentidad)}",nativeQuery=true)
	public List<RepCertificado5ta>  execProcCertificado5ta(
					@Param("codempresa") String codempresa,
					@Param("ano") String ano,
					@Param("mes") String mes,
					@Param("codpersonal") String codpersonal,					
					@Param("codusuario") String codusuario,					
					@Param("docidentidad") String docidentidad										
					);

    
}
