package com.tech.application.rest.models.services.service;

import java.util.List;
import java.util.Optional;

import com.tech.application.rest.models.entity.MaeUsuario;

public interface IUsuarioService {

	public MaeUsuario findById(Integer id);
	
	Optional<MaeUsuario> findByUsuario(String usuario);
	Optional<MaeUsuario> findByEmail(String email);
	
	public List<MaeUsuario> findAllByCliente(Integer cliente);
	public List<MaeUsuario> findAllByCompania(Integer compania);
	
	boolean existsByUsuario(String usuario);
	boolean existsByEmail(String email);
	
	public List<MaeUsuario> findAll();
	
	public MaeUsuario save(MaeUsuario cliente);
	public void delete(Integer id);

	/*
	boolean verifyCaptcha(String token, String remoteIp);

	boolean isBlocked(String ip);

	void loginFailed(String ip);

	void loginSucceeded(String ip);
	*/
}
