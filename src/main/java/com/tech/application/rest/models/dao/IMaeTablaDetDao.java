package com.tech.application.rest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tech.application.rest.models.entity.MaeTablaDet;

@Repository
public interface IMaeTablaDetDao extends JpaRepository<MaeTablaDet, Integer>{


	@Query(nativeQuery = true, 
			value= "select id_tabla,id_tabla_det,codigo,nombre,valor_ini,valor_fin,ind_visible from mae_tabla_det\r\n"
			+ "where id_tabla_det = :id")
	public MaeTablaDet BuscarById(@Param("id") Integer id);

	@Query(nativeQuery = true, 
			value= "select id_tabla,id_tabla_det,codigo,nombre,valor_ini,valor_fin,ind_visible from mae_tabla_det\r\n"
			+ "where id_tabla = :tabla")
	public List<MaeTablaDet> BuscarAllByTabla(@Param("tabla") Integer tabla);
	
	@Query(nativeQuery = true, 
			value= "select id_tabla,id_tabla_det,codigo,nombre,valor_ini,valor_fin,ind_visible from mae_tabla_det\r\n"
			+ "where   id_tabla in (select id_tabla from mae_tabla where id_compania = :compania and tipo_tabla = :tipo)")
	public List<MaeTablaDet> BuscarByCompaniaTipo(@Param("compania") Integer compania,
														@Param("tipo") String tipo);
	
	
}
