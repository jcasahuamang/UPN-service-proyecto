package com.tech.application.rest.models.services.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech.application.rest.models.dao.IMaeCompaniaDao;
import com.tech.application.rest.models.entity.MaeCompania;
import com.tech.application.rest.models.services.service.IMaeCompaniaService;

@Service
public class MaeCompaniaServiceImpl implements IMaeCompaniaService{

	@Autowired
	private IMaeCompaniaDao maeCompaniaDao;
	
	@Override
	@Transactional(readOnly = true)	
	public MaeCompania BuscarById(String id){
		return maeCompaniaDao.BuscarById(id);
	}

	
	@Override
	@Transactional(readOnly = true)	
	public List<MaeCompania> BuscarAllByUsuario(String usuario) {
        return maeCompaniaDao.BuscarAllByUsuario(usuario);
	}

	@Override
	@Transactional(readOnly = true)	
	public List<MaeCompania> BuscarAll() {
        return maeCompaniaDao.BuscarAll();
	}
	
}
