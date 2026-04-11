package com.tech.application.rest.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tech.application.rest.models.entity.IDatosPersonal;
import com.tech.application.rest.models.entity.PlaPersonal;
import com.tech.application.rest.models.entity.keys.PlaPersonalId;


@Repository
public interface IPlaPersonalDao extends JpaRepository<PlaPersonal, PlaPersonalId>{

    	@Query(nativeQuery = true, 
			value= "select COD_EMPRESA,COD_PERSONAL,COD_TIPO_PLANILLA,APE_PATERNO,APE_MATERNO,NOM_TRABAJADOR,FEC_INGRESO,FEC_CESADO,TIP_ESTADO,TIP_SEXO,TIP_ESTADO_CIVIL,FEC_NACIMIENTO,IND_ASIG_FAMILIAR,NUM_HIJOS,COD_CATEGORIA,COD_CARGO,COD_SUCURSAL,COD_C_COSTOS,TIP_PENSION,COD_AFP,COD_UNICO_SPP,IND_COMISION_MIXTA,IND_JUBILADO_AFP,COD_AUXILIAR_BANCO_CUENTA,NUM_CUENTA_BANCO_PAGO,COD_MONEDA_PAGO,IND_NETO,SUELDO,SUELDO_NETO,ind_ria,IND_RIA_VAC,IND_RIA_CTS,IND_RIA_GRATI from PLA_PERSONAL "+
             "where COD_EMPRESA = :codempresa and cod_personal = :codpersonal")
	public PlaPersonal BuscarById(@Param("codempresa") String codempresa,
                                    @Param("codpersonal") String codpersonal);



    @Query(value="{call sp_skm_consulta_dato_personal(:codempresa,:codpersonal,:codusuario)}",nativeQuery=true)
	public IDatosPersonal  execProcDataPersonal(
					@Param("codempresa") String codempresa,
					@Param("codpersonal") String codpersonal,					
					@Param("codusuario") String codusuario										
					);

}
