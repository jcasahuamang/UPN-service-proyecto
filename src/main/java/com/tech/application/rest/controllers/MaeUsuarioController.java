package com.tech.application.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.application.rest.models.entity.MaeUsuario;
import com.tech.application.rest.models.services.service.IUsuarioService;


@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/usuario")
public class MaeUsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/lista")
	public List<MaeUsuario> findAll(){
		return usuarioService.findAll();
	}
	
	@GetMapping("/lista/{cliente}")
	public List<MaeUsuario> findAllCliente(@PathVariable Integer cliente){
		return usuarioService.findAllByCliente(cliente);
	}	
	

	@GetMapping("/listacompa/{compania}")
	public List<MaeUsuario> findAllCompania(@PathVariable Integer compania){
		return usuarioService.findAllByCompania(compania);
	}	
	
	
}
