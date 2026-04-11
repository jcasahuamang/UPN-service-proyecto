package com.tech.application.rest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tech.application.rest.models.entity.RepBoletaCts;

@Repository
public interface IRepBoletaCtsDao extends JpaRepository<RepBoletaCts, Integer>{
    
    @Query(value="{call sp_SKM_GENERA_BOLETA_CTS(:codempresa,:ano,:mes,:codpersonal)}",nativeQuery=true)
	public List<RepBoletaCts>  execProcBoletaCts(
					@Param("codempresa") String codempresa,
					@Param("ano") String ano,
					@Param("mes") String mes,
					@Param("codpersonal") String codpersonal	
					);

}
