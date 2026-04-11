package com.tech.application.rest.models.services.service;

import java.util.List;

import com.tech.application.rest.models.entity.RepBoletaCts;
import com.tech.application.rest.models.entity.RepBoletaPago;
import com.tech.application.rest.models.entity.RepCertificado5ta;

public interface IRepDocumentoPersonalService {
    
    	public List<RepBoletaPago>  execProcBoletaPago( String codempresa, String ano,String mes, String version, String codpersonal, String codusuario,String docidentidad);	

		public List<RepCertificado5ta>  execProcCertificado5ta( String codempresa, String ano,String mes,String codpersonal, String codusuario,String docidentidad);	

		public List<RepBoletaCts>  execProcBoletaCts( String codempresa, String ano,String mes,String codpersonal);	

		public Integer ValidaVisualizacion(String codempresa,String anoproceso,String mesproceso,String version,String codpersonal,String codusuario,String numdocidentidad,String tipdocumento);


}
