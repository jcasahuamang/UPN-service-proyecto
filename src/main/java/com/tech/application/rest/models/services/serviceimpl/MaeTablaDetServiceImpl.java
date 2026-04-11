package com.tech.application.rest.models.services.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech.application.rest.models.dao.IMaeTablaDetDao;
import com.tech.application.rest.models.entity.MaeTablaDet;
import com.tech.application.rest.models.services.service.IMaeTablaDetService;

@Service
public class MaeTablaDetServiceImpl implements IMaeTablaDetService{

	@Autowired
	private IMaeTablaDetDao maeTablaDetDao;

	@Override
	@Transactional(readOnly = true)	
	public MaeTablaDet BuscarById(Integer id){
		return maeTablaDetDao.BuscarById(id);
	}

	@Override
	@Transactional(readOnly = true)	
	public List<MaeTablaDet> BuscarAllByTabla(Integer tabla) {
        return maeTablaDetDao.BuscarAllByTabla(tabla);
	}

	@Override
	@Transactional(readOnly = true)	
	public List<MaeTablaDet> BuscarByCompaniaTipo(Integer compania,String tipo){
        return maeTablaDetDao.BuscarByCompaniaTipo(compania,tipo);
	}
	
	@Override
	@Transactional
	public MaeTablaDet save(MaeTablaDet tabla) {
		return maeTablaDetDao.save(tabla);
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		maeTablaDetDao.deleteById(id);
	}
	
	@Override
	@Transactional
	public Iterable<MaeTablaDet> saveAll(List<MaeTablaDet> tablaDetEntities){
		return maeTablaDetDao.saveAll(tablaDetEntities);
	}	
	
	@Override
	@Transactional
	public void deleteAll(List<MaeTablaDet> tablaDetEntities){
		maeTablaDetDao.deleteAll(tablaDetEntities);
	}	
	
	
}
