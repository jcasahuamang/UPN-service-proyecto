package com.tech.application.rest.models.services.shared;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.application.rest.models.dao.shared.ISubMenuModuloDao;
import com.tech.application.rest.models.entity.shared.SubMenuModulo;

@Service 
public class SubMenuModuloServiceImpl implements ISubMenuModuloService{

	@Autowired
	private ISubMenuModuloDao subMenuModuloDao;
	
	
	@Override
	public List<SubMenuModulo> execProcGetSubModuloUsuario(String usuario,Integer id_modulo,Integer id_funcion_sup) {
		return subMenuModuloDao.execProcGetSubModuloUsuario(usuario,id_modulo,id_funcion_sup);
	}
	
	@Override
	public List<SubMenuModulo> execProcGetSubUrlUsuario(String usuario,String url) {
		return subMenuModuloDao.execProcGetSubUrlUsuario(usuario,url);
	}	
}
