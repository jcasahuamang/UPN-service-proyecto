package com.tech.application.rest.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tech.application.rest.models.entity.MaeCompania;
import com.tech.application.rest.models.services.service.IArchivoService;
import com.tech.application.rest.models.services.service.IMaeCompaniaService;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/archivos")
public class SkmArchivoController {

    @Autowired
	private IMaeCompaniaService maeCompaniaService;

    @Autowired
    private IArchivoService archivoService;


    @GetMapping("/descargar/{tiparchivo}/{empresa}")
	public ResponseEntity<Resource> getFile(
        @PathVariable String tiparchivo,
            @PathVariable String empresa
            ){

        String nombreArchivo = "";
        MaeCompania compania= null;
        compania = maeCompaniaService.BuscarById(empresa);

        nombreArchivo = archivoService.ObtieneRutaImagen(tiparchivo,"ARCHIVO", compania.getNumrucempresa());
		Resource file = archivoService.downloadFile(nombreArchivo);
		
		return ResponseEntity.ok().
				header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nombreArchivo + "\"")
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(file);
	}



	@PostMapping("/cargar/file")
	public Long UploadFileExpedienteCab(
            @RequestParam("file") MultipartFile file,
            @RequestParam("tiparchivo") String tiparchivo, 
			@RequestParam("empresa") String empresa, 
            @RequestParam("usuario") String usuario)
			throws IllegalStateException, IOException, ParseException {

		//String name;
        MaeCompania compania= null;
        compania = maeCompaniaService.BuscarById(empresa);

		// GRABAR ARCHIVO EN CARPETA
		//name = 
        archivoService.uploadFile(tiparchivo,compania.getNumrucempresa(),file);
                
		return 1L;
	}
}

