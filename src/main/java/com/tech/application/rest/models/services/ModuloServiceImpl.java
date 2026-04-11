package com.tech.application.rest.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.tech.application.rest.models.dao.IModuloDao;
import com.tech.application.rest.models.entity.Modulo;

@Service
public class ModuloServiceImpl implements IModuloService{
	@Autowired
	private IModuloDao moduloDao;
	
	@Override
	@Transactional(readOnly = true)
	public Modulo findById(Integer id) {
		return moduloDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Modulo> findByCodigo(String codigo) {
        return moduloDao.findByCodigo(codigo);
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean existsByCodigo(String codigo) {
        return moduloDao.existsByCodigo(codigo);		
	}	
	
	@Override
	@Transactional(readOnly = true)
	public List<Modulo> findAll() {
		return (List<Modulo>)moduloDao.findAll();
	}

	@Override
	@Transactional
	public Modulo save(Modulo modulo) {
		return moduloDao.save(modulo);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		moduloDao.deleteById(id);
	
	}
	
}
