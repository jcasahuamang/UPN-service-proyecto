package com.tech.application.rest.models.services.service;

import java.util.List;

import com.tech.application.rest.models.entity.MaeTablaDet;

public interface IMaeTablaDetService {
	

	public MaeTablaDet BuscarById(Integer id);
	
	public List<MaeTablaDet> BuscarAllByTabla(Integer tabla);

	public List<MaeTablaDet> BuscarByCompaniaTipo(Integer compania,String tipo);
	
	public MaeTablaDet save(MaeTablaDet tabladet);
	
	public void delete(Integer id);

	public Iterable<MaeTablaDet> saveAll(List<MaeTablaDet> tablaDetEntities);
	
	public void deleteAll(List<MaeTablaDet> tablaDetEntities);	

}
