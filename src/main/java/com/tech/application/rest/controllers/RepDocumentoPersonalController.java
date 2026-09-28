package com.tech.application.rest.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
//import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.application.rest.models.entity.RepBoletaCts;
import com.tech.application.rest.models.entity.RepBoletaPago;
import com.tech.application.rest.models.entity.RepCertificado5ta;
import com.tech.application.rest.models.services.service.IArchivoService;
import com.tech.application.rest.models.services.service.IRepDocumentoPersonalService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.tech.application.rest.security.service.AccesoDocumentoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/personaldoc")
public class RepDocumentoPersonalController {
	private static final Logger logger = LoggerFactory.getLogger(RepDocumentoPersonalController.class);

	/** Código para el frontend: no se pudo validar el periodo; no debe generarse el documento. */
	public static final int VALIDACION_ERROR = -1;

	@Autowired
    private IRepDocumentoPersonalService repDocumentoPersonalService;

	@Autowired
	private AccesoDocumentoService accesoDocumentoService;

	@Value("${resources.images}")
	private String rutaImagenes;

	@Autowired
    private IArchivoService archivoService;

@GetMapping("/valida/{codempresa}/{ano}/{mes}/{version}/{codpersonal}/{codusuario}/{docidentidad}/{tipdocumento}")
public ResponseEntity<Integer> ValidarVisualizacion(
    @PathVariable String codempresa,
    @PathVariable String ano,
    @PathVariable String mes,
    @PathVariable String version,
    @PathVariable String codpersonal,
    @PathVariable String codusuario,
    @PathVariable String docidentidad,
    @PathVariable String tipdocumento) {

    if (!accesoDocumentoService.esDelUsuarioAutenticado(codempresa, codpersonal, codusuario, docidentidad)) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    try {
        Integer retorno = repDocumentoPersonalService.ValidaVisualizacion(codempresa, ano, mes, version,
                codpersonal, codusuario, docidentidad, tipdocumento);
        if (retorno == null) {
            logger.warn("La validación no devolvió resultado (empresa={}, personal={}, documento={})",
                    codempresa, codpersonal, tipdocumento);
            return ResponseEntity.ok(VALIDACION_ERROR);
        }
        return ResponseEntity.ok(retorno);
    } catch (DataAccessException e) {
        logger.error("Error de base de datos al validar la visualización del documento {}", tipdocumento, e);
        return ResponseEntity.ok(VALIDACION_ERROR);
    }
}
	


	
    @GetMapping("/boletapago/data/{codempresa}/{ano}/{mes}/{version}/{codpersonal}/{codusuario}/{docidentidad}")
	public ResponseEntity<?> BoletaPagoData(
		@PathVariable String codempresa,
		@PathVariable String ano,
		@PathVariable String mes,
		@PathVariable String version,
		@PathVariable String codpersonal,
		@PathVariable String codusuario,
		@PathVariable String docidentidad){		
				
		if (!accesoDocumentoService.esDelUsuarioAutenticado(codempresa, codpersonal, codusuario, docidentidad)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}			

		List<RepBoletaPago> reporte= null;
		Map<String, Object> response = new HashMap<>();		
		
		
		try {
			reporte = repDocumentoPersonalService.execProcBoletaPago(codempresa,ano,mes,version,codpersonal,codusuario,docidentidad);

		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<RepBoletaPago>>(reporte,HttpStatus.OK);
	}		
  
	@GetMapping("/boletapago/pdf/{codempresa}/{ano}/{mes}/{version}/{codpersonal}/{codusuario}/{docidentidad}")
	public ResponseEntity<byte[]> BoletaPagoPdf(
		@PathVariable String codempresa,
		@PathVariable String ano,
		@PathVariable String mes,
		@PathVariable String version,
		@PathVariable String codpersonal,
		@PathVariable String codusuario,
		@PathVariable String docidentidad) throws JRException,DataAccessException, FileNotFoundException{
		
		if (!accesoDocumentoService.esDelUsuarioAutenticado(codempresa, codpersonal, codusuario, docidentidad)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);		
		headers.setContentDispositionFormData("boletapago","boletapago.pdf");

		String logoPath = "";
		String firmaPath = "";

		List<RepBoletaPago> reporte = repDocumentoPersonalService.execProcBoletaPago(codempresa, ano, mes, version,
				codpersonal, codusuario, docidentidad);
		if (sinDatos(reporte)) {
			logger.warn("Sin datos para generar la boleta de pago (empresa={}, personal={}, periodo={}/{})",
					codempresa, codpersonal, ano, mes);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		logoPath = archivoService.ObtieneRutaImagen("LOGO","COMPLETA", reporte.get(0).getNUM_RUC_EMPRESA());
		firmaPath = archivoService.ObtieneRutaImagen("FIRMA","COMPLETA", reporte.get(0).getNUM_RUC_EMPRESA());

		String dia ="";

		switch (mes) {
			case "01": case "03": case "05": case "07":
			case "08": case "10": case "12":
				dia = "31";
				break;
			case "04": case "06": case "09": case "11":
				dia = "30";
				break;
			case "02":
				dia = "28";
				break;
			default:
				dia = "30";
			}


		Map<String, Object> parameters = new HashMap<>();	
		parameters.put("p_des_mes", mes);
		parameters.put("p_ano", ano);
//		parameters.put("p_fecha_pago", "15/"+mes+"/"+ano);
		parameters.put("p_fecha_pago", dia+"/"+mes+"/"+ano);				
//      parameters.put("conceptoDataSet", conceptoDataSet);		
		parameters.put("p_logo", logoPath);
		parameters.put("p_firma", firmaPath);

		JasperReport report = JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:\\templates\\UniversitarioBoletaPago.jrxml").getPath());
		JasperPrint print = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(reporte));
		return ResponseEntity.ok().headers(headers).body( JasperExportManager.exportReportToPdf(print));

	}			

	@GetMapping("/boletapago/pdf2/{codempresa}/{ano}/{mes}/{version}/{codpersonal}/{codusuario}/{docidentidad}")
	public ResponseEntity<?> BoletaPagoPdfxxxx(@PathVariable String codempresa,@PathVariable String ano,
			@PathVariable String mes,@PathVariable String version,@PathVariable String codpersonal,
            @PathVariable String codusuario,@PathVariable String docidentidad) throws IOException{

		if (!accesoDocumentoService.esDelUsuarioAutenticado(codempresa, codpersonal, codusuario, docidentidad)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
//		MaeCompania compania= null;
		List<RepBoletaPago> reporte=  new ArrayList<>();;
		Map<String, Object> response = new HashMap<>();		
		
		String filePath = "D:\\Desarrollo\\Spring5\\workspace\\service-kiosko\\src\\main\\resources\\templates\\ParedesBoletaPago2.jrxml";

		String logoPath = "";
		String firmaPath = "";

		String fileDestination = "D:\\Desarrollo\\Spring5\\"+codempresa+ano+mes+version+docidentidad+".pdf";
		try {

			reporte = repDocumentoPersonalService.execProcBoletaPago(codempresa,ano,mes,version,codpersonal,codusuario,docidentidad);

			logoPath = rutaImagenes+"\\"+reporte.get(0).getNUM_RUC_EMPRESA()+"_logo.jpg";
			firmaPath = rutaImagenes+"\\"+reporte.get(0).getNUM_RUC_EMPRESA()+"_firma.jpg";
	
			if (!new File(logoPath).exists()){			
				logoPath = rutaImagenes+"\\logoblanco.jpg";
			}
			if (!new File(firmaPath).exists()){			
				firmaPath = rutaImagenes+"\\firmablanco.jpg";
			}
	

			try {
				/*
				List<RepBoletaPagoDet> detalle = new ArrayList<>();

				for(RepBoletaPago boletadet: reporte){
					RepBoletaPagoDet det = new RepBoletaPagoDet(
						(boletadet.getIN_DESC() == null) ? "" : boletadet.getIN_DESC(),
						boletadet.getIN_IMP(),
						(boletadet.getDE_DESC() == null) ? "" : boletadet.getDE_DESC(),
						boletadet.getDE_IMP(),
						(boletadet.getAP_DESC() == null) ? "" : boletadet.getAP_DESC(),
						boletadet.getAP_IMP()); 
					detalle.add(det);
				}
				JRBeanCollectionDataSource conceptoDataSet = new JRBeanCollectionDataSource(detalle);
				*/
				/*
				byte[] logo = compania.getBmplogo();
				byte[] firma = compania.getBmpfirma();
				ByteArrayInputStream bislogo = new ByteArrayInputStream(logo);
				BufferedImage imagelogo = ImageIO.read(bislogo);

				*/

				String dia = "";

					switch (mes) {
						case "01": case "03": case "05": case "07":
						case "08": case "10": case "12":
							dia = "31";
							break;
						case "04": case "06": case "09": case "11":
							dia = "30";
							break;
						case "02":
							dia = "28";
							break;
						default:
							dia = "30";
						}
				
				Map<String, Object> parameters = new HashMap<>();	
				parameters.put("p_des_mes", mes);
				parameters.put("p_ano", ano);
//				parameters.put("p_fecha_pago", "15/"+mes+"/"+ano);
				parameters.put("p_fecha_pago", dia+"/"+mes+"/"+ano);				
//				parameters.put("conceptoDataSet", conceptoDataSet);		
				parameters.put("p_logo", logoPath);
				parameters.put("p_firma", firmaPath);

				JasperReport report = JasperCompileManager.compileReport(filePath);
				JasperPrint print = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(reporte));
				JasperExportManager.exportReportToPdfFile(print,fileDestination);

				response.put("mensaje", "Se genero el documento en la ruta: ".concat(fileDestination));
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);

			} catch (JRException e) {
				response.put("mensaje", "Error al generar el documento en PDF.");
				response.put("error", e.getMessage());
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				}


		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		//return new ResponseEntity<List<RepBoletaPago>>(reporte,HttpStatus.OK);
	}			

	/********************  CERTIFICADO DE QUINTA **********************************************/
	@GetMapping("/certificadoqta/data/{codempresa}/{ano}/{mes}/{codpersonal}/{codusuario}/{docidentidad}")
	public ResponseEntity<?> Certificado5taData(@PathVariable String codempresa,
	@PathVariable String ano,
	@PathVariable String mes,
	@PathVariable String codpersonal,
	@PathVariable String codusuario,
	@PathVariable String docidentidad){
		
		if (!accesoDocumentoService.esDelUsuarioAutenticado(codempresa, codpersonal, codusuario, docidentidad)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		List<RepCertificado5ta> reporte= null;
		Map<String, Object> response = new HashMap<>();		
		
		
		try {
			reporte = repDocumentoPersonalService.execProcCertificado5ta(codempresa,ano,mes,codpersonal,codusuario,docidentidad);

		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<RepCertificado5ta>>(reporte,HttpStatus.OK);
	}


	@GetMapping("/certificadoqta/pdf/{codempresa}/{ano}/{mes}/{codpersonal}/{codusuario}/{docidentidad}")
	public ResponseEntity<byte[]> Certificado5taPdf(@PathVariable String codempresa,
	@PathVariable String ano,
	@PathVariable String mes,
	@PathVariable String codpersonal,
	@PathVariable String codusuario,
	@PathVariable String docidentidad) throws JRException,DataAccessException, FileNotFoundException{

		if (!accesoDocumentoService.esDelUsuarioAutenticado(codempresa, codpersonal, codusuario, docidentidad)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		String logoPath = "";
		String firmaPath = "";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);		
		headers.setContentDispositionFormData("certificado5ta","certificado5ta.pdf");


		List<RepCertificado5ta> reporte = repDocumentoPersonalService.execProcCertificado5ta(codempresa, ano, mes,
				codpersonal, codusuario, docidentidad);
		if (sinDatos(reporte)) {
			logger.warn("Sin datos para generar el certificado de quinta (empresa={}, personal={}, año={})",
					codempresa, codpersonal, ano);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
			
		logoPath = archivoService.ObtieneRutaImagen("LOGO","COMPLETA", reporte.get(0).getC_ruc_empresa());
		firmaPath = archivoService.ObtieneRutaImagen("FIRMA","COMPLETA", reporte.get(0).getC_ruc_empresa());

		Map<String, Object> parameters = new HashMap<>();	
		parameters.put("p_logo", logoPath);
		parameters.put("p_firma", firmaPath);

		
		JasperReport report = JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:\\templates\\UniversitarioCertificado5ta.jrxml").getPath());
		JasperPrint print = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(reporte));
		return ResponseEntity.ok().headers(headers).body( JasperExportManager.exportReportToPdf(print));
	}			

	/********************  BOLETA DE CTS **********************************************/
	@GetMapping("/boletacts/data/{codempresa}/{ano}/{mes}/{codpersonal}")
	public ResponseEntity<?> BoletaCtsData(@PathVariable String codempresa,
	@PathVariable String ano,
	@PathVariable String mes,
	@PathVariable String codpersonal){
		
		if (!accesoDocumentoService.esDelUsuarioAutenticado(codempresa, codpersonal, null,null)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		List<RepBoletaCts> reporte= null;
		Map<String, Object> response = new HashMap<>();		
		
		
		try {
			reporte = repDocumentoPersonalService.execProcBoletaCts(codempresa,ano,mes,codpersonal);

		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<RepBoletaCts>>(reporte,HttpStatus.OK);
	}

	@GetMapping("/boletacts/pdf/{codempresa}/{ano}/{mes}/{codpersonal}")
	public ResponseEntity<byte[]> BoletaCtsPdf(@PathVariable String codempresa,
	@PathVariable String ano,
	@PathVariable String mes,
	@PathVariable String codpersonal) throws JRException,DataAccessException, FileNotFoundException{

		if (!accesoDocumentoService.esDelUsuarioAutenticado(codempresa, codpersonal, null,null)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
				
		String logoPath = "";
		String firmaPath = "";


		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);		
		headers.setContentDispositionFormData("boletacts","boletacts.pdf");

				
		List<RepBoletaCts> reporte = repDocumentoPersonalService.execProcBoletaCts(codempresa, ano, mes, codpersonal);
		if (sinDatos(reporte)) {
			logger.warn("Sin datos para generar la boleta CTS (empresa={}, personal={}, periodo={}/{})",
					codempresa, codpersonal, ano, mes);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}


		logoPath = archivoService.ObtieneRutaImagen("LOGO","COMPLETA", reporte.get(0).getNum_ruc_empresa());
		firmaPath = archivoService.ObtieneRutaImagen("FIRMA","COMPLETA", reporte.get(0).getNum_ruc_empresa());

		Map<String, Object> parameters = new HashMap<>();	
		parameters.put("p_logo", logoPath);
		parameters.put("p_firma", firmaPath);

		JasperReport report = JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:\\templates\\UniversitarioBoletaCTS.jrxml").getPath());
		JasperPrint print = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(reporte));
		return ResponseEntity.ok().headers(headers).body( JasperExportManager.exportReportToPdf(print));

		}		


		/** INS-03: indica si el procedimiento no devolvió datos para generar el documento. */
		private static boolean sinDatos(List<?> reporte) {
			return reporte == null || reporte.isEmpty();
		}
}
