package com.tech.application.rest.models.dao.shared;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.tech.application.rest.models.entity.shared.SubMenuModulo;

public interface ISubMenuModuloDao extends JpaRepository<SubMenuModulo,Integer>{

	@Query(value="{call usp_get_funcion_usuario(:usuario,:id_modulo,:id_funcion_sup)}",nativeQuery=true)
	List<SubMenuModulo> execProcGetSubModuloUsuario(
					@Param("usuario") String usuario,
					@Param("id_modulo") Integer id_modulo,
					@Param("id_funcion_sup") Integer id_funcion_sup);
	
	@Query(value="{call usp_get_funcion_url_usuario(:usuario,:url)}",nativeQuery=true)
	List<SubMenuModulo> execProcGetSubUrlUsuario(
					@Param("usuario") String usuario,
					@Param("url") String url);	
	
}
