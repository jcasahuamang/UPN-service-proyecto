package com.tech.application.rest.models.services.service;

import java.util.List;

import com.tech.application.rest.models.entity.MaeTabla;

public interface IMaeTablaService {


	public MaeTabla BuscarById(Integer id);

	public List<MaeTabla> BuscarAllByCompania(Integer compania);

	public List<MaeTabla> BuscarByCompaniaTipo(Integer compania,String tipo);
	
	public MaeTabla save(MaeTabla tabla);
	
	public void delete(Integer id);

	public int execProcDeleteTabla(String usuario,Integer cliente,Integer tabla);	
	
}
