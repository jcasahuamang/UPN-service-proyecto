package com.tech.application.rest.models.services.service;
import java.util.List;

import com.tech.application.rest.models.entity.MaeCompania;

public interface IMaeCompaniaService {

	public MaeCompania BuscarById(String id);
	public List<MaeCompania> BuscarAllByUsuario(String usuario);
	public List<MaeCompania> BuscarAll();
	
}
