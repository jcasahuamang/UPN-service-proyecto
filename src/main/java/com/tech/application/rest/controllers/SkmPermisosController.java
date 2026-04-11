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

import com.tech.application.rest.models.entity.IRelacionPermisos;
import com.tech.application.rest.models.entity.SkmPermisos;
import com.tech.application.rest.models.entity.ValidacionRespuesta;
import com.tech.application.rest.models.services.service.ISkmPermisosService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/permiso")
public class SkmPermisosController {

    @Autowired
    private ISkmPermisosService skmPermisosService;
    
    @GetMapping("/{id}")
	public ResponseEntity<?> BuscarById(@PathVariable Long id) {
		SkmPermisos permiso= null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			permiso = skmPermisosService.BuscarById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		if(permiso.getId() == null) {
			response.put("mensaje", "El permiso : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SkmPermisos>(permiso,HttpStatus.OK);		
	}

    @GetMapping("/all")
	public ResponseEntity<?> BuscarAll(){
		List<SkmPermisos> permiso= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			permiso = skmPermisosService.BuscarAll();
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<SkmPermisos>>(permiso,HttpStatus.OK);
	}

    
	@GetMapping("/all/{compania}")
	public ResponseEntity<?> BuscarAllByCompania(@PathVariable String compania){
		List<SkmPermisos> permiso = null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			permiso = skmPermisosService.BuscarByEmpresa(compania);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<SkmPermisos>>(permiso,HttpStatus.OK);
	}

    @GetMapping("/all/{compania}/{personal}")
	public ResponseEntity<?> BuscarAllByCompaniaPersonal(
        @PathVariable String compania,
        @PathVariable String personal){
		List<SkmPermisos> permiso = null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			permiso = skmPermisosService.BuscarByEmpresaPersonal(compania,personal);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<SkmPermisos>>(permiso,HttpStatus.OK);
	}

	
	@GetMapping("/valida/{empresa}/{personal}/{tiporegistro}/{idper}/{fecinicio}/{fecfin}")
	public ResponseEntity<?> ValidaPermisos(
				@PathVariable String empresa,
				@PathVariable String personal,
				@PathVariable String tiporegistro,
				@PathVariable Long idper,
				@PathVariable String fecinicio,
				@PathVariable String fecfin){
		
		List<ValidacionRespuesta> validacion= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			validacion = skmPermisosService.ProcValidaPermisos(empresa,personal,tiporegistro,idper,fecinicio,fecfin);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<ValidacionRespuesta>>(validacion,HttpStatus.OK);
	}		

	@GetMapping("/consulta/{empresa}/{planilla}/{estado}/{personal}/{usuario}")
	public ResponseEntity<?> ConsultaPermisos(
				@PathVariable String empresa,
				@PathVariable String planilla,
				@PathVariable String estado,
				@PathVariable String personal,
				@PathVariable String usuario){
		
		List<IRelacionPermisos> validacion= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			validacion = skmPermisosService.ProcConsultaPermisos(empresa,planilla,estado,personal,usuario);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<IRelacionPermisos>>(validacion,HttpStatus.OK);
	}				

    /*************************************************************************************************/
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody SkmPermisos permiso) {
		SkmPermisos permisoNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			permisoNew = skmPermisosService.save(permiso);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El permiso ha sido registrado con exito!");		
		response.put("Anuncio", permisoNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);

	}	

    @PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody SkmPermisos permiso,@PathVariable Long id) {
		SkmPermisos permisoActual = skmPermisosService.BuscarById(id);
		SkmPermisos permisoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(permisoActual == null) {
			response.put("mensaje", "Error: no se pudo editar, permiso : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			permisoActual.setEmpresa(permiso.getEmpresa());
			permisoActual.setCodpersonal(permiso.getCodpersonal());
			permisoActual.setFecinicio(permiso.getFecinicio());
			permisoActual.setFecfin(permiso.getFecfin());
			permisoActual.setNumdias(permiso.getNumdias());
			permisoActual.setObservacion(permiso.getObservacion());
			permisoActual.setTipo(permiso.getTipo());
			permisoActual.setEstado(permiso.getEstado());
			permisoActual.setUsucreacion(permiso.getUsucreacion());
			permisoActual.setFeccreacion(permiso.getFeccreacion());
			permisoActual.setCodigotransferencia(permiso.getCodigotransferencia());

			
			permisoUpdated = skmPermisosService.save(permisoActual);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el permiso en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El permiso ha sido actualizado con exito!");		
		response.put("Permiso", permisoUpdated);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			skmPermisosService.delete(id);			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el permiso de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "el permiso ha sido eliminado con exito!");		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);		
	}

}
