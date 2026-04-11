package com.tech.application.rest.models.services.shared;

import com.tech.application.rest.models.dao.shared.IMenuModuloDao;
import com.tech.application.rest.models.entity.shared.MenuModulo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MenuModuloServiceImpl implements IMenuModuloService{
	
	@Autowired
	private IMenuModuloDao menuModuloDao;
	

	@Override
	public List<MenuModulo> execProcGetModuloUsuario(String usuario) {
		return menuModuloDao.execProcGetModuloUsuario(usuario);
	}
	
	

}
