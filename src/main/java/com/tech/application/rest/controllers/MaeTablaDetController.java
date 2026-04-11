package com.tech.application.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.application.rest.models.entity.MaeTablaDet;
import com.tech.application.rest.models.entity.configurations.TablaGeneral;
import com.tech.application.rest.models.services.service.IMaeTablaDetService;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/maetabladet")
public class MaeTablaDetController {

	@Autowired
	private IMaeTablaDetService maeTablaDetService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> BuscarById(@PathVariable Integer id) {
		MaeTablaDet tablaDet = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			tablaDet = maeTablaDetService.BuscarById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		if(tablaDet.getId() == null) {
			response.put("mensaje", "La Tabla : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MaeTablaDet>(tablaDet,HttpStatus.OK);		
	}
	
	@GetMapping("/lista/{tabla}")
	public ResponseEntity<?> BuscarAllByTabla(@PathVariable Integer tabla){
		List<MaeTablaDet> tablaDet= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			tablaDet = maeTablaDetService.BuscarAllByTabla(tabla);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<MaeTablaDet>>(tablaDet,HttpStatus.OK);
	}		
	/*************************************************/
	@GetMapping("/listaByTipo/{compania}/{tipo}")
	public ResponseEntity<?> BuscarByCompaniaTipo(@PathVariable Integer compania,@PathVariable String tipo){
		List<MaeTablaDet> tablaDet = null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			TablaGeneral tabla = new TablaGeneral();
			String tipoTabla = tabla.getTipoTabla(tipo);

			//tablaDet = maeTablaDetService.BuscarByCompaniaTipo(compania,tipo);
			tablaDet = maeTablaDetService.BuscarByCompaniaTipo(compania,tipoTabla);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<MaeTablaDet>>(tablaDet,HttpStatus.OK);
	}
	
	/*****************************************************************/
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody MaeTablaDet tablaDet) {
		MaeTablaDet tablaDetNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			tablaDetNew = maeTablaDetService.save(tablaDet);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La Tabla ha sido creada con exito!");		
		response.put("Tabla", tablaDetNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody MaeTablaDet tablaDet,@PathVariable Integer id) {
		MaeTablaDet tablaDetActual = maeTablaDetService.BuscarById(id);
		MaeTablaDet tablaDetUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(tablaDetActual == null) {
			response.put("mensaje", "Error: no se pudo editar, La Tabla : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			tablaDetActual.setIdTabla(tablaDet.getIdTabla());
			tablaDetActual.setCodigo(tablaDet.getCodigo());
			tablaDetActual.setNombre(tablaDet.getNombre());
			tablaDetActual.setValorIni(tablaDet.getValorIni());
			tablaDetActual.setValorFin(tablaDet.getValorFin());			
			tablaDetActual.setIndVisible(tablaDet.getIndVisible());
			
			tablaDetUpdated = maeTablaDetService.save(tablaDetActual);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la tabla en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La tabla ha sido actualizado con exito!");		
		response.put("Tabla", tablaDetUpdated);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}

	@PutMapping("/saveAll")
	public ResponseEntity<?> updateAll(@RequestBody List<MaeTablaDet> tablaDet) {
		  Map<String, Object> response = new HashMap<>();
		  List<MaeTablaDet> tablaDetEntities = new ArrayList<>();

		    for (MaeTablaDet detalle : tablaDet) {
//		    	tablaDetEntities.add(dtoToEntity(detalle));
		    	tablaDetEntities.add(detalle);		    	
		    }
		/*
		if (tablaDetEntities == null) {
			response.put("mensaje", "Error: no se pudo editar, El detalle de la tabla no se pudo armar");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		*/
		try {
//		    Iterable<MaeTablaDet> persistedTablaDet = maeTablaDetService.saveAll(tablaDetEntities);	
				maeTablaDetService.saveAll(tablaDetEntities);	
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la tabla en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La tabla detalle ha sido actualizado con exito!");		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}


	/*************  ELIMINACION *******************************************************************/

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			maeTablaDetService.delete(id);			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar la tabla en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La tabla ha sido eliminado con exito!");		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);		
	}		
	
	@PutMapping("/deleteAll")
	public ResponseEntity<?> deleteAll(@RequestBody List<MaeTablaDet> tablaDet) {
		  Map<String, Object> response = new HashMap<>();
		  List<MaeTablaDet> tablaDetEntities = new ArrayList<>();

		    for (MaeTablaDet detalle : tablaDet) {
		    	tablaDetEntities.add(detalle);		    	
		    }
		/*
		if (tablaDetEntities == null) {
			response.put("mensaje", "Error: no se pudo editar, El detalle de la tabla no se pudo armar");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		*/
		try {
		    maeTablaDetService.deleteAll(tablaDetEntities);	
		    
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar la tabla en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La tabla detalle ha sido eliminado con exito!");		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	/*
	https://stackoverflow.com/questions/72040490/how-to-save-array-of-object-in-sprig-boot-with-crudrepository
	https://www.baeldung.com/spring-data-save-saveall
	
		*/
}
