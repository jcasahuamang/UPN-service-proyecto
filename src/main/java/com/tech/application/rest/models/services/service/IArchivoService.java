package com.tech.application.rest.models.services.service;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IArchivoService {
    
    public Resource downloadFile(String filename);
    public String ObtieneRutaImagen(String tiparchivo,String tipoRutaRetorno,String num_ruc);
	public String GeneraFileName(String tiparchivo,String num_ruc,MultipartFile multiPart);
	public String uploadFile(String tiparchivo,String num_ruc,MultipartFile file)throws IllegalStateException, IOException;

    public String ObtieneRutaReglamento(String tipoRutaRetorno,String num_ruc,String nombreArchivo);

}
