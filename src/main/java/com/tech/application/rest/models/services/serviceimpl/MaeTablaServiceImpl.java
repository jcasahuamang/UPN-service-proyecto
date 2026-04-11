package com.tech.application.rest.models.services.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech.application.rest.models.dao.IMaeTablaDao;
import com.tech.application.rest.models.entity.MaeTabla;
import com.tech.application.rest.models.services.service.IMaeTablaService;

@Service
public class MaeTablaServiceImpl implements IMaeTablaService {

	@Autowired
	private IMaeTablaDao maeTablaDao;
	
	@Override
	@Transactional(readOnly = true)	
	public MaeTabla BuscarById(Integer id){
		return maeTablaDao.BuscarById(id);
	}

	@Override
	@Transactional(readOnly = true)	
	public List<MaeTabla> BuscarAllByCompania(Integer compania) {
        return maeTablaDao.BuscarAllByCompania(compania);
	}

	@Override
	@Transactional(readOnly = true)	
	public List<MaeTabla> BuscarByCompaniaTipo(Integer compania,String tipo){
        return maeTablaDao.BuscarByCompaniaTipo(compania,tipo);
	}
	
	@Override
	@Transactional
	public MaeTabla save(MaeTabla tabla) {
		return maeTablaDao.save(tabla);
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		maeTablaDao.deleteById(id);
	}
	
	@Override
	public int execProcDeleteTabla(String usuario,Integer cliente,Integer tabla) {
		return maeTablaDao.execProcDeleteTabla(usuario,cliente,tabla);
	}
	
	
	
}
