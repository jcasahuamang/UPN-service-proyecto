package com.tech.application.rest.models.services.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech.application.rest.models.dao.ISkmAprobacionesDao;
import com.tech.application.rest.models.entity.SkmAprobaciones;
import com.tech.application.rest.models.entity.ValidacionRespuesta;
import com.tech.application.rest.models.services.service.ISkmAprobacionesService;

@Service
public class SkmAprobacionesServiceImpl implements ISkmAprobacionesService{
    
        @Autowired
    private ISkmAprobacionesDao skmAprobacionesDao;
    
    @Override
    public SkmAprobaciones BuscarById(Long id) {
        return skmAprobacionesDao.BuscarById(id);
    }

    @Override
    public List<SkmAprobaciones> BuscarAll() {
        return skmAprobacionesDao.BuscarAll();  
    }

    @Override
	@Transactional(readOnly = true)	
	public List<ValidacionRespuesta> execProcValidaAprobaciones(String llave,String tipoaprobacion,String accion,String usuario) {
        return skmAprobacionesDao.execProcValidaAprobaciones(llave,tipoaprobacion,accion,usuario);
	}

    @Override
	@Transactional(readOnly = true)	
	public Integer EjecutaAprobaciones(String llave,String tipoaprobacion,String accion,String usuario) {
        return skmAprobacionesDao.execProcEjecutaAprobaciones(llave,tipoaprobacion,accion,usuario);
	}

    @Override
	@Transactional
	public SkmAprobaciones save(SkmAprobaciones aprobaciones) {
		return skmAprobacionesDao.save(aprobaciones);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		skmAprobacionesDao.deleteById(id);
	}


    @Override
	@Transactional
	public Iterable<SkmAprobaciones> saveAll(List<SkmAprobaciones> entities){
		return skmAprobacionesDao.saveAll(entities);
	}	
	
	@Override
	@Transactional
	public void deleteAll(List<SkmAprobaciones> entities){
		skmAprobacionesDao.deleteAll(entities);
	}	


}
