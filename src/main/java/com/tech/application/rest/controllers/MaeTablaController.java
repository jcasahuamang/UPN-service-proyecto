package com.tech.application.rest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.application.rest.models.entity.MaeTabla;
import com.tech.application.rest.models.services.service.IMaeTablaService;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/maetabla")
public class MaeTablaController {

	@Autowired
	private IMaeTablaService maeTablaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> BuscarById(@PathVariable Integer id) {
		MaeTabla tabla = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			tabla = maeTablaService.BuscarById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		if(tabla.getId() == null) {
			response.put("mensaje", "La Tabla : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MaeTabla>(tabla,HttpStatus.OK);		
	}
	
	@GetMapping("/lista/{compania}")
	public ResponseEntity<?> BuscarAllByCompania(@PathVariable Integer compania){
		List<MaeTabla> tabla= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			tabla = maeTablaService.BuscarAllByCompania(compania);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<MaeTabla>>(tabla,HttpStatus.OK);
	}		
	
	/*************************************************/
	@GetMapping("/listaByTipo/{compania}/{tipo}")
	public ResponseEntity<?> BuscarByCompaniaTipo(@PathVariable Integer compania,@PathVariable String tipo){
		List<MaeTabla> tabla = null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			tabla = maeTablaService.BuscarByCompaniaTipo(compania,tipo);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<MaeTabla>>(tabla,HttpStatus.OK);
	}
	
	/*************************************************/
	@GetMapping("/existetipo/{compania}/{tipo}")
	public Boolean existeByCompaniaTipo(@PathVariable Integer compania,@PathVariable String tipo){
		List<MaeTabla> tabla = maeTablaService.BuscarByCompaniaTipo(compania,tipo);

		if(tabla.size() > 0) {
			//1L
			return true;
		}else {
			//0L
			return false;
		}
	}
	
	/*****************************************************************/
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody MaeTabla tabla) {
		MaeTabla tablaNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			tablaNew = maeTablaService.save(tabla);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La Tabla ha sido creada con exito!");		
		response.put("Tabla", tablaNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody MaeTabla tabla,@PathVariable Integer id) {
		MaeTabla tablaActual = maeTablaService.BuscarById(id);
		MaeTabla tablaUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(tablaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, La Tabla : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			tablaActual.setIdCompania(tabla.getIdCompania());
			tablaActual.setTipoTabla(tabla.getTipoTabla());
			tablaActual.setNombre(tabla.getNombre());
			tablaActual.setIndSistema(tabla.getIndSistema());
			
			tablaUpdated = maeTablaService.save(tablaActual);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la tabla en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La tabla ha sido actualizado con exito!");		
		response.put("Tabla", tablaUpdated);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
/*************  ELIMINACION *******************************************************************/
	
	@GetMapping("/delete/{usuario}/{cliente}/{tabla}")
	public ResponseEntity<?> delete(@PathVariable String usuario,@PathVariable Integer cliente,@PathVariable Integer tabla) {
		Map<String, Object> response = new HashMap<>();
		
		try {
//			int numero = maeTablaService.execProcDeleteTabla(usuario,cliente,tabla);
			maeTablaService.execProcDeleteTabla(usuario,cliente,tabla);			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar la compañia en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La Tabla ha sido eliminada con exito!");		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);		
	}	


	
}
