package com.tech.application.rest.models.services;

import java.util.List;
import java.util.Optional;

import com.tech.application.rest.models.entity.Modulo;

public interface IModuloService {

	public Modulo findById(Integer id);
	
	Optional<Modulo> findByCodigo(String codigo);
	
	boolean existsByCodigo(String codigo);
	
	public List<Modulo> findAll();
	
	public Modulo save(Modulo modulo);
	public void delete(Integer id);
	
}
