package com.tech.application.rest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tech.application.rest.models.entity.MaeTabla;

@Repository
public interface IMaeTablaDao extends JpaRepository<MaeTabla, Integer>{


	@Query(nativeQuery = true, 
			value= "select id_compania,id_tabla,tipo_tabla,nombre,ind_sistema from mae_tabla\r\n"
			+ "where id_tabla = :id")
	public MaeTabla BuscarById(@Param("id") Integer id);

	@Query(nativeQuery = true, 
			value= "select id_compania,id_tabla,tipo_tabla,nombre,ind_sistema from mae_tabla\r\n"
			+ "where id_compania = :compania")
	public List<MaeTabla> BuscarAllByCompania(@Param("compania") Integer compania);
	
	@Query(nativeQuery = true, 
			value= "select id_compania,id_tabla,tipo_tabla,nombre,ind_sistema from mae_tabla\r\n"
			+ "where id_compania = :compania and tipo_tabla = :tipo")
	public List<MaeTabla> BuscarByCompaniaTipo(@Param("compania") Integer compania,
														@Param("tipo") String tipo);
	
	@Query(value="{call usp_delete_tabla(:usuario,:cliente,:tabla)}",nativeQuery=true)
	public int execProcDeleteTabla(
					@Param("usuario") String usuario,
					@Param("cliente") Integer cliente,
					@Param("tabla") Integer tabla);
	
	
}
