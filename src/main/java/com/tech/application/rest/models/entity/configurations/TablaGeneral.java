package com.tech.application.rest.models.entity.configurations;

import java.util.HashMap;
import java.util.Map;

public class TablaGeneral {

	private String tipo_tabla= "";
	
	public TablaGeneral() {}

	public String getTipoTabla(String valor){
		
	     Map<String, String> map = new HashMap<String, String>();
	     
	     map.put("SIS_TIPALMA", "SIS_TIPALMA");		//TIPO DE ALMACEN
	     map.put("SIS_TIPCCOS", "SIS_TIPCCOS");		//TIPO DE C.COSTO
	     
	     map.put("SIS_TDOCENT", "SIS_TDOCENT");		//TIPO DE DOCUMENTO ENTIDAD
	     map.put("SIS_TIPENT", "SIS_TIPENT");	    //TIPO ENTIDAD
	     map.put("SIS_TMOVALM", "SIS_TMOVALM");	    //TIPO MOVIMIENTO ALMACEN
	     
	     
	     tipo_tabla = map.get(valor);
	     return tipo_tabla;
	}
	 
}
