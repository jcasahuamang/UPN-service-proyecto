package com.tech.application.rest.models.services.shared;


import java.util.List;

import com.tech.application.rest.models.entity.shared.MenuModulo;

public interface IMenuModuloService {

	public List<MenuModulo> execProcGetModuloUsuario(String usuario);
}
