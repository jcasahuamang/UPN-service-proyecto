package com.tech.application.rest.controllers;

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

import com.tech.application.rest.models.entity.IRelacionVacaciones;
import com.tech.application.rest.models.entity.SkmVacaciones;
import com.tech.application.rest.models.entity.ValidacionRespuesta;
import com.tech.application.rest.models.services.service.ISkmVacacionesService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vacacion")
public class SkmVacacionesController {
    @Autowired
    private ISkmVacacionesService skmVacacionesService;
    
    @GetMapping("/{id}")
	public ResponseEntity<?> BuscarById(@PathVariable Long id) {
		SkmVacaciones vacacion= null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			vacacion = skmVacacionesService.BuscarById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		if(vacacion.getId() == null) {
			response.put("mensaje", "Las vacaciones : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SkmVacaciones>(vacacion,HttpStatus.OK);		
	}

    @GetMapping("/all")
	public ResponseEntity<?> BuscarAll(){
		List<SkmVacaciones> vacacion= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			vacacion = skmVacacionesService.BuscarAll();
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<SkmVacaciones>>(vacacion,HttpStatus.OK);
	}

    
	@GetMapping("/all/{compania}")
	public ResponseEntity<?> BuscarAllByCompania(@PathVariable String compania){
		List<SkmVacaciones> vacacion = null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			vacacion = skmVacacionesService.BuscarByEmpresa(compania);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<SkmVacaciones>>(vacacion,HttpStatus.OK);
	}

    @GetMapping("/all/{compania}/{personal}")
	public ResponseEntity<?> BuscarAllByCompaniaPersonal(
        @PathVariable String compania,
        @PathVariable String personal){
		List<SkmVacaciones> vacacion = null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			vacacion = skmVacacionesService.BuscarByEmpresaPersonal(compania,personal);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<SkmVacaciones>>(vacacion,HttpStatus.OK);
	}

	
	@GetMapping("/valida/{empresa}/{personal}/{tiporegistro}/{idvac}/{fecinicio}/{fecfin}")
	public ResponseEntity<?> ValidaVacaciones(
				@PathVariable String empresa,
				@PathVariable String personal,
				@PathVariable String tiporegistro,
				@PathVariable Long idvac,
				@PathVariable String fecinicio,
				@PathVariable String fecfin){
		
		List<ValidacionRespuesta> validacion= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			validacion = skmVacacionesService.ProcValidaVacaciones(empresa,personal,tiporegistro,idvac,fecinicio,fecfin);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<ValidacionRespuesta>>(validacion,HttpStatus.OK);
	}		

	@GetMapping("/consulta/{empresa}/{planilla}/{estado}/{personal}/{usuario}")
	public ResponseEntity<?> ConsultaVacaciones(
				@PathVariable String empresa,
				@PathVariable String planilla,
				@PathVariable String estado,
				@PathVariable String personal,
				@PathVariable String usuario){
		
		List<IRelacionVacaciones> validacion= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			validacion = skmVacacionesService.ProcConsultaVacaciones(empresa,planilla,estado,personal,usuario);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<IRelacionVacaciones>>(validacion,HttpStatus.OK);
	}		


    /*************************************************************************************************/
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody SkmVacaciones vacacion) {
		SkmVacaciones vacacionNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			vacacionNew = skmVacacionesService.save(vacacion);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Las vacaciones han sido registradas con exito!");		
		response.put("Anuncio", vacacionNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);

	}	

    @PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody SkmVacaciones vacacion,@PathVariable Long id) {
		SkmVacaciones vacacionActual = skmVacacionesService.BuscarById(id);
		SkmVacaciones vacacionUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(vacacionActual == null) {
			response.put("mensaje", "Error: no se pudo editar, Vacaciones : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			vacacionActual.setEmpresa(vacacion.getEmpresa());
			vacacionActual.setCodpersonal(vacacion.getCodpersonal());
			vacacionActual.setFecinicio(vacacion.getFecinicio());
			vacacionActual.setFecfin(vacacion.getFecfin());
			vacacionActual.setNumdias(vacacion.getNumdias());
			vacacionActual.setObservacion(vacacion.getObservacion());
			vacacionActual.setTipo(vacacion.getTipo());
			vacacionActual.setEstado(vacacion.getEstado());
			vacacionActual.setUsucreacion(vacacion.getUsucreacion());
			vacacionActual.setFeccreacion(vacacion.getFeccreacion());
			vacacionActual.setCodigotransferencia(vacacion.getCodigotransferencia());

			
			vacacionUpdated = skmVacacionesService.save(vacacionActual);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar las vacacionens en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Las vacaciones han sido actualizado con exito!");		
		response.put("Anuncio", vacacionUpdated);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			skmVacacionesService.delete(id);			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar las vacaciones de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Las vacaciones ha sido eliminado con exito!");		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);		
	}		


}
