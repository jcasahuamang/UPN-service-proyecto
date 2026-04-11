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

import com.tech.application.rest.models.entity.SkmAprobaciones;
import com.tech.application.rest.models.entity.ValidacionRespuesta;
import com.tech.application.rest.models.services.service.ISkmAprobacionesService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/aprobacion")
public class SkmAprobacionesController {
 
    @Autowired
    private ISkmAprobacionesService skmAprobacionesService;
    
    @GetMapping("/{id}")
	public ResponseEntity<?> BuscarById(@PathVariable Long id) {
		SkmAprobaciones aprobacion= null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			aprobacion = skmAprobacionesService.BuscarById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		if(aprobacion.getId() == null) {
			response.put("mensaje", "Aprobacion : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SkmAprobaciones>(aprobacion,HttpStatus.OK);		
	}

    @GetMapping("/all")
	public ResponseEntity<?> BuscarAll(){
		List<SkmAprobaciones> aprobacion= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			aprobacion = skmAprobacionesService.BuscarAll();
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<SkmAprobaciones>>(aprobacion,HttpStatus.OK);
	}

    @GetMapping("/valida/{llave}/{tipoaprobacion}/{accion}/{usuario}")
	public ResponseEntity<?> ValidaAprobaciones(
				@PathVariable String llave,
				@PathVariable String tipoaprobacion,
				@PathVariable String accion,
				@PathVariable String usuario){
		
		List<ValidacionRespuesta> validacion= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			validacion = skmAprobacionesService.execProcValidaAprobaciones(llave,tipoaprobacion,accion,usuario);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<ValidacionRespuesta>>(validacion,HttpStatus.OK);
	}	


    @PostMapping("/ejecuta")
	public Integer ProcesoEjecutaAprobaciones(@RequestBody Map<String, Object> request) {

		String llave = (String) request.get("llave");
        String tipoaprobacion = (String) request.get("tipoaprobacion");
        String accion = (String) request.get("accion");
        String usuario = (String) request.get("usuario");

        Integer retorno = 0;

		try {
			retorno = skmAprobacionesService.EjecutaAprobaciones(llave,tipoaprobacion,accion,usuario);

		} catch(DataAccessException e) {
			return 0;
			
		}

		return retorno;		
	}


        /*************************************************************************************************/
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody SkmAprobaciones aprobacion) {
		SkmAprobaciones aprobacionNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			aprobacionNew = skmAprobacionesService.save(aprobacion);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La aprobacion han sido registradas con exito!");		
		response.put("Anuncio", aprobacionNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);

	}	

    @PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody SkmAprobaciones aprobacion,@PathVariable Long id) {
		SkmAprobaciones aprobacionActual = skmAprobacionesService.BuscarById(id);
		SkmAprobaciones aprobacionUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(aprobacionActual == null) {
			response.put("mensaje", "Error: no se pudo editar, Aprobacion : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			aprobacionActual.setLlave(aprobacion.getLlave());
			aprobacionActual.setTipoaprobacion(aprobacion.getTipoaprobacion());
            aprobacionActual.setAccion(aprobacion.getAccion());
            aprobacionActual.setUsuario(aprobacion.getUsuario());
            aprobacionActual.setEmpresa(aprobacion.getEmpresa());
            aprobacionActual.setPersonal(aprobacion.getPersonal());
            aprobacionActual.setIdsolicitud(aprobacion.getIdsolicitud());
			
			aprobacionUpdated = skmAprobacionesService.save(aprobacionActual);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la aprobacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La aprobacion han sido actualizado con exito!");		
		response.put("Anuncio", aprobacionUpdated);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}

    
    @PutMapping("/saveAll")
	public ResponseEntity<?> saveAll(@RequestBody List<SkmAprobaciones> aprobacion) {
		  Map<String, Object> response = new HashMap<>();
		  List<SkmAprobaciones> aprobacionEntities = new ArrayList<>();

		    for (SkmAprobaciones detalle : aprobacion) {
		    	aprobacionEntities.add(detalle);		    	
		    }

         try {
		    @SuppressWarnings("unused")
			Iterable<SkmAprobaciones> persistedAprobacion = skmAprobacionesService.saveAll(aprobacionEntities);	
		    
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la tabla en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El registro ha sido actualizado con exito!");		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			skmAprobacionesService.delete(id);			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar la aprobacion de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La aprobacion ha sido eliminada con exito!");		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);		
	}

    @PutMapping("/deleteAll")
	public ResponseEntity<?> deleteAll(@RequestBody List<SkmAprobaciones> aprobacion) {
		  Map<String, Object> response = new HashMap<>();
		  List<SkmAprobaciones> aprobacionEntities = new ArrayList<>();

		    for (SkmAprobaciones detalle : aprobacion) {
		    	aprobacionEntities.add(detalle);		    	
		    }

		try {
			skmAprobacionesService.deleteAll(aprobacionEntities);	
		    
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar la tabla en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El registro ha sido eliminado con exito!");		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}

}
