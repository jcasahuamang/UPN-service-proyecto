package com.tech.application.rest.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.application.rest.security.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	
	boolean existsByNombreUsuario(String nombreUsuario);
	boolean existsByEmail(String email);

}
