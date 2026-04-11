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

import com.tech.application.rest.models.entity.IRelacionPrestamos;
import com.tech.application.rest.models.entity.SkmPrestamos;
import com.tech.application.rest.models.entity.ValidacionRespuesta;
import com.tech.application.rest.models.services.service.ISkmPrestamosService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/prestamo")
public class SkmPrestamosController {
    
    
    @Autowired
    private ISkmPrestamosService skmPrestamosService;
    
    @GetMapping("/{id}")
	public ResponseEntity<?> BuscarById(@PathVariable Long id) {
		SkmPrestamos prestamo= null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			prestamo = skmPrestamosService.BuscarById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		if(prestamo.getId() == null) {
			response.put("mensaje", "El prestamo : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SkmPrestamos>(prestamo,HttpStatus.OK);		
	}

    @GetMapping("/all")
	public ResponseEntity<?> BuscarAll(){
		List<SkmPrestamos> prestamo= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			prestamo = skmPrestamosService.BuscarAll();
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<SkmPrestamos>>(prestamo,HttpStatus.OK);
	}

    
	@GetMapping("/all/{compania}")
	public ResponseEntity<?> BuscarAllByCompania(@PathVariable String compania){
		List<SkmPrestamos> prestamo = null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			prestamo = skmPrestamosService.BuscarByEmpresa(compania);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<SkmPrestamos>>(prestamo,HttpStatus.OK);
	}

    @GetMapping("/all/{compania}/{personal}")
	public ResponseEntity<?> BuscarAllByCompaniaPersonal(
        @PathVariable String compania,
        @PathVariable String personal){
		List<SkmPrestamos> prestamo = null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			prestamo = skmPrestamosService.BuscarByEmpresaPersonal(compania,personal);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<SkmPrestamos>>(prestamo,HttpStatus.OK);
	}

	
	@GetMapping("/valida/{empresa}/{personal}/{tiporegistro}/{id}/{fecsolicitud}/{moneda}/{importe}/{cuota}")
	public ResponseEntity<?> ValidaPrestamos(
				@PathVariable String empresa,
				@PathVariable String personal,
				@PathVariable String tiporegistro,
				@PathVariable Long id,
				@PathVariable String fecsolicitud,
				@PathVariable String moneda,
                @PathVariable Double importe,
                @PathVariable Integer cuota){
		
		List<ValidacionRespuesta> validacion= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			validacion = skmPrestamosService.ProcValidaPrestamos(empresa,personal,tiporegistro,id,fecsolicitud,moneda,importe,cuota);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<ValidacionRespuesta>>(validacion,HttpStatus.OK);
	}		

	@GetMapping("/consulta/{empresa}/{planilla}/{estado}/{personal}/{usuario}")
	public ResponseEntity<?> ConsultaPrestamos(
				@PathVariable String empresa,
				@PathVariable String planilla,
				@PathVariable String estado,
				@PathVariable String personal,
				@PathVariable String usuario){
		
		List<IRelacionPrestamos> validacion= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			validacion = skmPrestamosService.ProcConsultaPrestamos(empresa,planilla,estado,personal,usuario);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<IRelacionPrestamos>>(validacion,HttpStatus.OK);
	}			

    /*************************************************************************************************/
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody SkmPrestamos prestamo) {
		SkmPrestamos prestamoNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			prestamoNew = skmPrestamosService.save(prestamo);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El prestamo ha sido registrado con exito!");		
		response.put("Anuncio", prestamoNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);

	}	

    @PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody SkmPrestamos prestamo,@PathVariable Long id) {
		SkmPrestamos prestamoActual = skmPrestamosService.BuscarById(id);
		SkmPrestamos prestamoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(prestamoActual == null) {
			response.put("mensaje", "Error: no se pudo editar, Prestamo : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			prestamoActual.setEmpresa(prestamo.getEmpresa());
			prestamoActual.setCodpersonal(prestamo.getCodpersonal());
			prestamoActual.setTipo(prestamo.getTipo());
			prestamoActual.setFecsolicitud(prestamo.getFecsolicitud());
			prestamoActual.setMoneda(prestamo.getMoneda());
			prestamoActual.setImporte(prestamo.getImporte());
			prestamoActual.setCuotas(prestamo.getCuotas());
			prestamoActual.setObservacion(prestamo.getObservacion());
			prestamoActual.setEstado(prestamo.getEstado());
			prestamoActual.setUsucreacion(prestamo.getUsucreacion());
			prestamoActual.setFeccreacion(prestamo.getFeccreacion());
			prestamoActual.setCodigotransferencia(prestamo.getCodigotransferencia());
			
			prestamoUpdated = skmPrestamosService.save(prestamoActual);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el prestamo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El prestamo ha sido actualizado con exito!");		
		response.put("Anuncio", prestamoUpdated);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			skmPrestamosService.delete(id);			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el prestamo de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El prestamo ha sido eliminado con exito!");		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);		
	}		

}
