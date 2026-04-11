package com.tech.application.rest.models.services.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech.application.rest.models.dao.ISkmConfigVisualizacionDao;
import com.tech.application.rest.models.entity.ISkmPeriodoVisualizacion;
import com.tech.application.rest.models.entity.ISkmTipoPlanilla;
import com.tech.application.rest.models.entity.ISkmVisualizacion;
import com.tech.application.rest.models.services.service.ISkmConfigVisualizacionService;

@Service
public class SkmConfigVisualizacionServiceImpl implements ISkmConfigVisualizacionService {
    
    @Autowired
    private ISkmConfigVisualizacionDao skmConfigVisualizacionDao;


    @Override
	@Transactional(readOnly = true)	
	public Integer RegistraVisualizacion(String codempresa,String anoproceso,String mesproceso,String codpersonal,String codusuario,String numdocidentidad,String tipdocumento) {
        return skmConfigVisualizacionDao.execProcRegistraVisualizacion(codempresa, anoproceso,mesproceso,codpersonal, codusuario, numdocidentidad,tipdocumento);
	}

    @Override
	@Transactional(readOnly = true)	
	public List<ISkmVisualizacion> ConsultaVisualizacion(String codempresa,String planilla,String anoproceso,String mesproceso,String tipdocumento) {
        return skmConfigVisualizacionDao.execProcConsultaVisualizacion(codempresa,planilla,anoproceso,mesproceso,tipdocumento);
	}

    @Override
	@Transactional(readOnly = true)	
	public Integer ConfiguraVisualizacion(String codempresa,String anoproceso,String mesproceso,String codtipoplanilla,String tipdocumento,String accion) {
        return skmConfigVisualizacionDao.execProcConfiguravisualizacion(codempresa,anoproceso,mesproceso,codtipoplanilla,tipdocumento,accion);
	}

	@Override
	@Transactional(readOnly = true)	
	public List<ISkmPeriodoVisualizacion> PeriodoVisualizacion(String codempresa,String tipoplanilla,String tipdocumento,String anoproceso) {
        return skmConfigVisualizacionDao.execProcPeriodoVisualizacion(codempresa,tipoplanilla,tipdocumento,anoproceso);
	}

	@Override
	@Transactional(readOnly = true)	
	public List<ISkmTipoPlanilla> TipoPlanilla(String codempresa) {
        return skmConfigVisualizacionDao.execProcTipoPlanilla(codempresa);
	}

	@Override
	@Transactional(readOnly = true)	
	public Integer ActualizaUsuario(String usuario,String claveant,String clavenuevo) {
        return skmConfigVisualizacionDao.execActualizaUsuario(usuario,claveant,clavenuevo);
	}

}
