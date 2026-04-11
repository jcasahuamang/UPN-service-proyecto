package com.tech.application.rest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.tech.application.rest.models.entity.ISkmAnuncios;
import com.tech.application.rest.models.entity.SkmAnuncios;
import com.tech.application.rest.models.services.service.ISkmAnunciosService;
import com.tech.application.rest.models.services.serviceimpl.ExcelExportServiceImpl;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/anuncio")
public class SkmAnunciosController {
    
    @Autowired
    private ISkmAnunciosService skmAnunciosService;

    @Autowired
	private ExcelExportServiceImpl excelExportService;

	@GetMapping("/{id}")
	public ResponseEntity<?> BuscarById(@PathVariable Integer id) {
		SkmAnuncios anuncio= null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			anuncio = skmAnunciosService.BuscarById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		if(anuncio.getId() == null) {
			response.put("mensaje", "El anuncio : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SkmAnuncios>(anuncio,HttpStatus.OK);		
	}

    @GetMapping("/all")
	public ResponseEntity<?> BuscarAll(){
		List<SkmAnuncios> anuncio= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			anuncio = skmAnunciosService.BuscarAll();
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<SkmAnuncios>>(anuncio,HttpStatus.OK);
	}

    
	@GetMapping("/all/{compania}")
	public ResponseEntity<?> BuscarAllByCompania(@PathVariable String compania){
		List<SkmAnuncios> anuncio= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			anuncio = skmAnunciosService.BuscarByEmpresa(compania);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<SkmAnuncios>>(anuncio,HttpStatus.OK);
	}

	@GetMapping("/consultatodo/{estado}")
	public ResponseEntity<?> BuscarAllByEstado(@PathVariable Integer estado){
		List<ISkmAnuncios> anuncio= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			anuncio = skmAnunciosService.ConfiguraAnuncioTodos(estado);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<ISkmAnuncios>>(anuncio,HttpStatus.OK);
	}

	@GetMapping("/consultaalerta/{usuario}/{empresa}/{alcance}/{fecha}")
	public ResponseEntity<?> BuscarAllByEstado(
				@PathVariable String usuario,
				@PathVariable String empresa,
				@PathVariable Integer alcance,
				@PathVariable String fecha){
		List<ISkmAnuncios> anuncio= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			anuncio = skmAnunciosService.ConfiguraAnuncioAlerta(usuario,empresa,alcance,fecha);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<ISkmAnuncios>>(anuncio,HttpStatus.OK);
	}

    /*************************************************************************************************/
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody SkmAnuncios anuncio) {
		SkmAnuncios anuncioNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			anuncioNew = skmAnunciosService.save(anuncio);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La compania ha sido creada con exito!");		
		response.put("Anuncio", anuncioNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);

	}	

    @PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody SkmAnuncios anuncio,@PathVariable Integer id) {
		SkmAnuncios anuncioActual = skmAnunciosService.BuscarById(id);
		SkmAnuncios anuncioUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(anuncioActual == null) {
			response.put("mensaje", "Error: no se pudo editar, El anuncio : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			anuncioActual.setTitulo(anuncio.getTitulo());
			anuncioActual.setDescripcion(anuncio.getDescripcion());
			anuncioActual.setEstado(anuncio.getEstado());
			anuncioActual.setAlcance(anuncio.getAlcance());
			anuncioActual.setFeciniciovigencia(anuncio.getFeciniciovigencia());
			anuncioActual.setFecfinvigencia(anuncio.getFecfinvigencia());
			anuncioActual.setContenido(anuncio.getContenido());
            anuncioActual.setEmpresa(anuncio.getEmpresa());
            anuncioActual.setUsucreacion(anuncio.getUsucreacion());
            anuncioActual.setFeccreacion(anuncio.getFeccreacion());
			
			anuncioUpdated = skmAnunciosService.save(anuncioActual);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la sede en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El anuncio ha sido actualizado con exito!");		
		response.put("Anuncio", anuncioUpdated);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			skmAnunciosService.delete(id);			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el anuncio en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El anuncio ha sido eliminado con exito!");		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);		
	}		


	@PostMapping("/registravisualizacion")
	public Integer RegistrarVisualizacion(@RequestBody Map<String, Object> request) {

		Integer idanuncio = (Integer) request.get("idanuncio");
        String usuario = (String) request.get("usuario");
        String empresa = (String) request.get("empresa");

        Integer retorno = 0;

		try {
			retorno = skmAnunciosService.RegistraVisualizacion(idanuncio,usuario,empresa);

		} catch(DataAccessException e) {
			return 0;
			
		}

		return retorno;		
	}


	/*************  EXPORTACION A EXCEL*******************************************************************/
	@GetMapping("/todos/excel/{estado}")
	public ResponseEntity<Resource> ExportaExcelAllByCompania(
		@PathVariable Integer estado){
		
		String filename = "Alertas.xlsx";

		List<ISkmAnuncios> visualizacion= null;
		visualizacion = skmAnunciosService.ConfiguraAnuncioTodos(estado);
		
		InputStreamResource file = new InputStreamResource(excelExportService.exportExcelconsultaAlertaTodos(visualizacion));

	    return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
		        .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
		        .body(file);

	}
	
}
