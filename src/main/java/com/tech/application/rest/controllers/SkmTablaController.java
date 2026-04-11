package com.tech.application.rest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.application.rest.models.entity.SkmTabla;
import com.tech.application.rest.models.services.service.ISkmTablaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tabla")
public class SkmTablaController {

    @Autowired
    private ISkmTablaService skmTablaService;

    @GetMapping("/{id}")
	public ResponseEntity<?> BuscarById(@PathVariable Long id) {
		SkmTabla tabla= null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			tabla = skmTablaService.BuscarById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		if(tabla.getId() == null) {
			response.put("mensaje", "El registro : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SkmTabla>(tabla,HttpStatus.OK);		
	}

    @GetMapping("/all")
	public ResponseEntity<?> BuscarAll(){
		List<SkmTabla> tabla= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			tabla = skmTablaService.BuscarAll();
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<SkmTabla>>(tabla,HttpStatus.OK);
	}

    
	@GetMapping("/all/{compania}")
	public ResponseEntity<?> BuscarAllByCompania(@PathVariable String compania){
		List<SkmTabla> tabla = null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			tabla = skmTablaService.BuscarByEmpresa(compania);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<SkmTabla>>(tabla,HttpStatus.OK);
	}

    @GetMapping("/all/{compania}/{tipo}")
	public ResponseEntity<?> BuscarAllByCompaniaTabla(
        @PathVariable String compania,
        @PathVariable String tipo){
		List<SkmTabla> tabla = null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			tabla = skmTablaService.BuscarByEmpresaTabla(compania,tipo);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<SkmTabla>>(tabla,HttpStatus.OK);
	}


}
