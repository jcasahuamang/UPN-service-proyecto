package com.tech.application.rest.models.services.shared;

import java.util.List;


import com.tech.application.rest.models.entity.shared.SubMenuModulo;

public interface ISubMenuModuloService {
	public List<SubMenuModulo> execProcGetSubModuloUsuario(String usuario,Integer id_modulo,Integer id_funcion_sup);
	
	public List<SubMenuModulo> execProcGetSubUrlUsuario(String usuario,String url);
	
}
