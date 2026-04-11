package com.tech.application.rest.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.application.rest.models.entity.IDatosPersonal;
import com.tech.application.rest.models.services.service.IPlaPersonalService;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/personal")
public class PlaPersonalController {
    
    @Autowired
    private IPlaPersonalService plaPersonalService;


//    @GetMapping("/datos")
//	public ResponseEntity<?> BuscarAllByUsuario(@RequestBody Map<String, Object> request){
	@GetMapping("/datos/{codempresa}/{codpersonal}/{usuario}")
	public ResponseEntity<?> BuscarAllByUsuario(@PathVariable String codempresa,@PathVariable String codpersonal,@PathVariable String usuario){
			/*
		String codempresa = (String) request.get("codempresa");
		String codpersonal = (String) request.get("codpersonal");
		String usuario = (String) request.get("usuario");
		*/
		IDatosPersonal personal= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			personal = plaPersonalService.ObtenerDatoPersonal(codempresa,codpersonal,usuario);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		if(personal.getCodpersonal().isEmpty()) {
			response.put("mensaje", "No existen ningún personal asociado!");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}		
		return new ResponseEntity<IDatosPersonal>(personal,HttpStatus.OK);
	}		




}
