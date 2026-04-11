package com.tech.application.rest.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.application.rest.models.entity.MaeCompania;
import com.tech.application.rest.models.entity.SkmReglamentos;
import com.tech.application.rest.models.services.service.IArchivoService;
import com.tech.application.rest.models.services.service.IMaeCompaniaService;
import com.tech.application.rest.models.services.service.ISkmReglamentosService;

import net.sf.jasperreports.engine.JRException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reglamento")
public class SkmReglamentosController {

    @Autowired
    private ISkmReglamentosService skmReglamentosService;

	@Autowired
	private IMaeCompaniaService maeCompaniaService;

	@Autowired
    private IArchivoService archivoService;



    @GetMapping("/{id}")
	public ResponseEntity<?> BuscarById(@PathVariable Integer id) {
		SkmReglamentos reglamento= null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			reglamento = skmReglamentosService.BuscarById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		if(reglamento.getId() == null) {
			response.put("mensaje", "El reglamento : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SkmReglamentos>(reglamento,HttpStatus.OK);		
	}

    @GetMapping("/all")
	public ResponseEntity<?> BuscarAll(){
		List<SkmReglamentos> reglamento= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			reglamento = skmReglamentosService.BuscarAll();
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<SkmReglamentos>>(reglamento,HttpStatus.OK);
	}

    
	@GetMapping("/all/{compania}")
	public ResponseEntity<?> BuscarAllByCompania(@PathVariable String compania){
		List<SkmReglamentos> reglamento= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			reglamento = skmReglamentosService.BuscarByEmpresa(compania);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<SkmReglamentos>>(reglamento,HttpStatus.OK);
	}
 
	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> DescargarReglamento(@PathVariable Integer id) 
	throws JRException,DataAccessException, IOException{
		SkmReglamentos reglamento= null;
		MaeCompania compania= null;
		String pathReglamento = "";
				
		reglamento = skmReglamentosService.BuscarById(id);
		compania = maeCompaniaService.BuscarById(reglamento.getEmpresa());
		pathReglamento = archivoService.ObtieneRutaReglamento("COMPLETA", compania.getNumrucempresa(), reglamento.getRuta());

		// Leer archivo desde disco
			Path path = Paths.get(pathReglamento);
			byte[] contenido = Files.readAllBytes(path);

			// Obtener tipo MIME automáticamente
			String contentType = Files.probeContentType(path);
			if (contentType == null) {
				contentType = "application/octet-stream"; // fallback
			}

			// Obtener solo el nombre del archivo
			String nombreArchivo = path.getFileName().toString();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType(contentType));
			headers.setContentDispositionFormData("attachment", nombreArchivo);

			return ResponseEntity.ok()
					.headers(headers)
					.body(contenido);

	}			

}