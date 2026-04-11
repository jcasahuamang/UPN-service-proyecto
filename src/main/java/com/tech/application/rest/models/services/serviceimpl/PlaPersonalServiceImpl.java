package com.tech.application.rest.models.services.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech.application.rest.models.dao.IPlaPersonalDao;
import com.tech.application.rest.models.entity.IDatosPersonal;
import com.tech.application.rest.models.services.service.IPlaPersonalService;

@Service
public class PlaPersonalServiceImpl implements IPlaPersonalService{
 
    @Autowired
	private IPlaPersonalDao plaPersonalDao;


    @Override
	@Transactional(readOnly = true)	
	public IDatosPersonal ObtenerDatoPersonal(String codempresa,String codpersonal,String usuario) {
        return plaPersonalDao.execProcDataPersonal(codempresa,codpersonal,usuario);
	}
}
