package com.tech.application.rest.models.dao.shared;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.tech.application.rest.models.entity.shared.MenuModulo;

@Repository
public interface IMenuModuloDao extends JpaRepository<MenuModulo, Integer>{
	/*
	@Procedure(name = "usp_get_modulo_usuario")
	public List<MenuModulo> execProcGetModuloUsuario(String usuario);
	*/


	@Query(value="{call usp_get_modulo_usuario(:usuario)}",nativeQuery=true)
	List<MenuModulo> execProcGetModuloUsuario(@Param("usuario") String usuario);

	
}
