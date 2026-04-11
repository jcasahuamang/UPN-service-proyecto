package com.tech.application.rest.models.services.service;


import com.tech.application.rest.models.entity.IDatosPersonal;

public interface IPlaPersonalService {
    
    	public IDatosPersonal ObtenerDatoPersonal(String codempresa,String codpersonal,String usuario);
}
