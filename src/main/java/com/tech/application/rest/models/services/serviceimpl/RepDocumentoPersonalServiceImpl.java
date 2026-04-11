package com.tech.application.rest.models.services.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech.application.rest.models.dao.IRepBoletaCtsDao;
import com.tech.application.rest.models.dao.IRepBoletaPagoDao;
import com.tech.application.rest.models.dao.IRepCertificado5taDao;
import com.tech.application.rest.models.entity.RepBoletaCts;
import com.tech.application.rest.models.entity.RepBoletaPago;
import com.tech.application.rest.models.entity.RepCertificado5ta;
import com.tech.application.rest.models.services.service.IRepDocumentoPersonalService;

@Service
public class RepDocumentoPersonalServiceImpl implements IRepDocumentoPersonalService {

    @Autowired
    private IRepBoletaPagoDao RepBoletaPagoDao;

	@Autowired
    private IRepCertificado5taDao RepCertificado5taDao;

	@Autowired
    private IRepBoletaCtsDao RepBoletaCtsDao;

    @Override                       
	public List<RepBoletaPago> execProcBoletaPago(String codempresa,String ano,String mes,String version,String codpersonal,String codusuario,String docidentidad) {
		return RepBoletaPagoDao.execProcBoletaPago(codempresa,ano,mes,version,codpersonal, codusuario,docidentidad);
	}


    @Override                       
	public List<RepCertificado5ta> execProcCertificado5ta(String codempresa,String ano,String mes,String codpersonal,String codusuario,String docidentidad) {
		return RepCertificado5taDao.execProcCertificado5ta(codempresa,ano,mes,codpersonal, codusuario,docidentidad);
	}

	@Override                       
	public List<RepBoletaCts> execProcBoletaCts(String codempresa,String ano,String mes,String codpersonal) {
		return RepBoletaCtsDao.execProcBoletaCts(codempresa,ano,mes,codpersonal);
	}

	@Override
	@Transactional(readOnly = true)	
	public Integer ValidaVisualizacion(String codempresa,String anoproceso,String mesproceso,String version,String codpersonal,String codusuario,String numdocidentidad,String tipdocumento) {
        return RepBoletaPagoDao.execProcValidaVisualizacion(codempresa, anoproceso,mesproceso,version,codpersonal, codusuario, numdocidentidad,tipdocumento);
	}

}
