package com.tech.application.rest.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tech.application.rest.models.entity.MaeUsuario;

@Repository
public interface IUsuarioDao extends JpaRepository<MaeUsuario, Integer>{

	Optional<MaeUsuario> findByUsuario(String usuario);
	Optional<MaeUsuario> findByEmail(String email);
	
	@Query(nativeQuery = true, value= "select id_cliente,id_usuario,usuario,clave,correo,primer_nombre,segundo_nombre,apellidos,tipo,estado from mae_usuario\r\n"
			+ "where id_cliente = :cliente")
	public List<MaeUsuario> findAllByCliente(@Param("cliente") Integer cliente);
	
	@Query(nativeQuery = true, 
			value= "select id_cliente,id_usuario,usuario,clave,correo,primer_nombre,segundo_nombre,apellidos,tipo,estado from mae_usuario\r\n"
			+ " where id_usuario in (select id_usuario from mae_usuario_compania where id_compania = :compania) ")
	public List<MaeUsuario> findAllByCompania(@Param("compania") Integer compania);
	
	boolean existsByUsuario(String usuario);
	boolean existsByEmail(String email);	
}
