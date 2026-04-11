package com.tech.application.rest.models.services.serviceimpl;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tech.application.rest.models.services.service.IArchivoService;

@Service
public class ArchivoServiceImpl implements  IArchivoService{
    
    @Value("${resources.images}")
	private String rutaImagenes;
    
	@Value("${resources.reglamentos}")
	private String rutaReglamentos;

	private Path raizImagenes;
	private Path raizReglamentos;

//	private final Path raizImagenes = Paths.get("C:\\Aquarius\\Kiosko\\Imagenes");
//	private final Path raizImagenes = Paths.get("D:\\Desarrollo\\Spring5\\Imagenes");
//	 private final Path raizImagenes = Paths.get(rutaImagenes);	
	//se debe inicializar la variable raizImagenes en un PostConstruct porque el tipo de @value rutaImagenes
	//no se carga al inicio 
	@PostConstruct
	public void init(){
		raizImagenes = Paths.get(rutaImagenes);
		if (!raizImagenes.toFile().exists()) {
			raizImagenes.toFile().mkdirs();
		}
		raizReglamentos = Paths.get(rutaReglamentos);
		if (!raizReglamentos.toFile().exists()) {
			raizReglamentos.toFile().mkdirs();
		}
	}


    @Override
	public Resource downloadFile(String filename) {
		try {
			Path file = raizImagenes.resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}


	@Override
	public String uploadFile(String tiparchivo,String num_ruc,MultipartFile file) 
	throws IllegalStateException, IOException {
		String objectName = GeneraFileName(tiparchivo,num_ruc,file);

		//file.transferTo(new File(rootExpedientes + "\\" + objectName));
		file.transferTo(new File(raizImagenes + File.separator + objectName));
		return objectName;
	}

	@Override
	public String ObtieneRutaImagen(String tiparchivo,String tipoRutaRetorno,String num_ruc){
		String nombreArchivo="";
		String rutaCompleta="";
		String rutafinal ="";

		if (tiparchivo.toUpperCase().equals("LOGO") ){nombreArchivo = num_ruc+"_logo.jpg";}
		if (tiparchivo.toUpperCase().equals("FIRMA") ){nombreArchivo = num_ruc+"_firma.jpg";}

		
//		rutaCompleta = rutaImagenes+"\\"+nombreArchivo;
		rutaCompleta = rutaImagenes+File.separator+nombreArchivo;
		if (!new File(rutaCompleta).exists()){		
			//Si no hay con extension jpg, entonces que busque con extension bmp
			if (tiparchivo.toUpperCase().equals("LOGO")){nombreArchivo = num_ruc+"_logo.bmp";}
			if (tiparchivo.toUpperCase().equals("FIRMA") ){nombreArchivo = num_ruc+"_firma.bmp";}

			rutaCompleta = rutaImagenes+File.separator+nombreArchivo;
			if (!new File(rutaCompleta).exists()){		
				//Si no hay con extension bmp, entonces que busque con extension jpeg    
				if (tiparchivo.toUpperCase().equals("LOGO")){nombreArchivo = num_ruc+"_logo.jpeg";}
				if (tiparchivo.toUpperCase().equals("FIRMA") ){nombreArchivo = num_ruc+"_firma.jpeg";}

				
				rutaCompleta = rutaImagenes+File.separator+nombreArchivo;
				if (!new File(rutaCompleta).exists()){		
					//Si no hay con extension jpeg, entonces que busque con extension png    
					if (tiparchivo.toUpperCase().equals("LOGO")){nombreArchivo = num_ruc+"_logo.png";}
					if (tiparchivo.toUpperCase().equals("FIRMA") ){nombreArchivo = num_ruc+"_firma.png";}
				
				}
				

			}        
		}        
		

		rutaCompleta = rutaImagenes+File.separator+nombreArchivo;
		if (!new File(rutaCompleta).exists()){		
			if (tiparchivo.toUpperCase().equals("LOGO")){nombreArchivo = "logoblanco.jpg";}
			if (tiparchivo.toUpperCase().equals("FIRMA") ){nombreArchivo = "firmablanco.jpg";}
		}        

		rutaCompleta = rutaImagenes+File.separator+nombreArchivo;
		rutafinal = nombreArchivo;
		if (tipoRutaRetorno.toUpperCase().equals("COMPLETA")){rutafinal = rutaCompleta;}
		if (tipoRutaRetorno.toUpperCase().equals("ARCHIVO")){rutafinal = nombreArchivo;}
		return rutafinal;
	}

	@Override
	public String GeneraFileName(String tiparchivo,String num_ruc,MultipartFile multiPart) {
		String fileName = "";

		fileName = num_ruc+'_'+multiPart.getOriginalFilename();
		if (tiparchivo.toUpperCase().equals("LOGO")){fileName = num_ruc+"_logo.jpg";}
		if (tiparchivo.toUpperCase().equals("FIRMA") ){fileName = num_ruc+"_firma.jpg";}

		//    return new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
		return fileName;
    }


	@Override
	public String ObtieneRutaReglamento(String tipoRutaRetorno,String num_ruc,String nombreArchivo){
		String rutaCompleta="";
		String rutafinal ="";

//		nombreArchivo = num_ruc+"_logo.jpg";
//		rutaCompleta = raizReglamentos+"\\"+ruta;

		rutaCompleta = rutaReglamentos+File.separator+nombreArchivo;
		rutafinal = nombreArchivo;
		if (tipoRutaRetorno.toUpperCase().equals("COMPLETA")){rutafinal = rutaCompleta;}
		if (tipoRutaRetorno.toUpperCase().equals("ARCHIVO")){rutafinal = nombreArchivo;}

		return rutafinal;
	}
	

}
