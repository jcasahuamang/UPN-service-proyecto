package com.tech.application.rest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.application.rest.models.entity.ISkmPeriodoVisualizacion;
import com.tech.application.rest.models.entity.ISkmTipoPlanilla;
import com.tech.application.rest.models.entity.ISkmVisualizacion;
import com.tech.application.rest.models.services.service.ISkmConfigVisualizacionService;
import com.tech.application.rest.models.services.serviceimpl.ExcelExportServiceImpl;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/configuracion")
public class SkmConfigVisualizacionController {
    
    @Autowired
    private ISkmConfigVisualizacionService skmConfigVisualizacionService;
    
	@Autowired
	private ExcelExportServiceImpl excelExportService;

    @PostMapping("/registra")
	public Integer RegistrarVisualizacion(@RequestBody Map<String, Object> request) {

		String codempresa = (String) request.get("codempresa");
        String ano = (String) request.get("ano");
        String mes = (String) request.get("mes");
        String codpersonal = (String) request.get("codpersonal");
        String codusuario = (String) request.get("codusuario");
        String numdocidentidad = (String) request.get("numdocidentidad");
        String tipdocumento = (String) request.get("tipdocumento");


        Integer retorno = 0;

		try {
			retorno = skmConfigVisualizacionService.RegistraVisualizacion(codempresa,ano,mes,codpersonal,codusuario,numdocidentidad,tipdocumento);

		} catch(DataAccessException e) {
			return 0;
			
		}

		return retorno;		
	}

    
    @PostMapping("/configura")
	public Integer ConfiguraVisualizacion(@RequestBody Map<String, Object> request) {

		String codempresa = (String) request.get("codempresa");
        String ano = (String) request.get("ano");
        String mes = (String) request.get("mes");
        String codtipoplanilla = (String) request.get("codtipoplanilla");
        String tipdocumento = (String) request.get("tipdocumento");
        String accion = (String) request.get("accion");

        Integer retorno = 0;

		try {
			retorno = skmConfigVisualizacionService.ConfiguraVisualizacion(codempresa,ano,mes,codtipoplanilla,tipdocumento,accion);

		} catch(DataAccessException e) {
			return 0;
			
		}

		return retorno;		
	}

    @PostMapping("/usuario")
	public Integer ConfiguraUsuario(@RequestBody Map<String, Object> request) {

		String usuario = (String) request.get("usuario");
        String claveant = (String) request.get("claveant");
        String clavenuevo = (String) request.get("clavenuevo");

        Integer retorno = 0;

		try {
			retorno = skmConfigVisualizacionService.ActualizaUsuario(usuario,claveant,clavenuevo);

		} catch(DataAccessException e) {
			return 0;
			
		}

		return retorno;		
	}

	
    @GetMapping("/consultaplanilla/{codempresa}")
	public ResponseEntity<?> PeriodoVisualizacion(@PathVariable String codempresa){
		
		List<ISkmTipoPlanilla> planilla= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			planilla = skmConfigVisualizacionService.TipoPlanilla(codempresa);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<ISkmTipoPlanilla>>(planilla,HttpStatus.OK);
	}

	@GetMapping("/periodo/{codempresa}/{tipoplanilla}/{tipdocumento}/{anoproceso}")
	public ResponseEntity<?> PeriodoVisualizacion(@PathVariable String codempresa,
				@PathVariable String tipoplanilla,
				@PathVariable String tipdocumento,
				@PathVariable String anoproceso){
		
		List<ISkmPeriodoVisualizacion> visualizacion= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			visualizacion = skmConfigVisualizacionService.PeriodoVisualizacion(codempresa,tipoplanilla,tipdocumento,anoproceso);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<ISkmPeriodoVisualizacion>>(visualizacion,HttpStatus.OK);
	}

    @GetMapping("/auditoria/consulta/{codempresa}/{planilla}/{ano}/{mes}/{tipdocumento}")
	public ResponseEntity<?> ConsultaVisualizacion(@PathVariable String codempresa,
				@PathVariable String planilla,
				@PathVariable String ano,
				@PathVariable String mes,
				@PathVariable String tipdocumento){
		
		List<ISkmVisualizacion> visualizacion= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			visualizacion = skmConfigVisualizacionService.ConsultaVisualizacion(codempresa,planilla,ano,mes,tipdocumento);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<ISkmVisualizacion>>(visualizacion,HttpStatus.OK);
	}		

	/*************  EXPORTACION A EXCEL*******************************************************************/
	@GetMapping("/auditoria/excel/{codempresa}/{planilla}/{ano}/{mes}/{tipdocumento}")
	public ResponseEntity<Resource> ExportaExcelAllByCompania(
		@PathVariable String codempresa,
		@PathVariable String planilla,
		@PathVariable String ano,
		@PathVariable String mes,
		@PathVariable String tipdocumento){
		
		String filename = "Auditoria.xlsx";

		List<ISkmVisualizacion> visualizacion= null;
		visualizacion = skmConfigVisualizacionService.ConsultaVisualizacion(codempresa,planilla,ano,mes,tipdocumento);
		
		InputStreamResource file = new InputStreamResource(excelExportService.exportExcelAuditoria(visualizacion));

	    return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
		        .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
		        .body(file);

	}
}
