package com.tech.application.rest.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.tech.application.rest.models.entity.Modulo;


@Repository
public interface IModuloDao extends JpaRepository<Modulo, Integer>{

	Optional<Modulo> findByCodigo(String codigo);
	
	boolean existsByCodigo(String codigo);
}
