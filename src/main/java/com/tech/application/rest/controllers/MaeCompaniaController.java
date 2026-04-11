package com.tech.application.rest.controllers;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.tech.application.rest.models.entity.MaeCompania;
import com.tech.application.rest.models.services.ExchangeRateService;
import com.tech.application.rest.models.services.service.IMaeCompaniaService;
//import java.awt.image.BufferedImage;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/compania")
public class MaeCompaniaController {

	@Autowired
	private IMaeCompaniaService maeCompaniaService;

	@Autowired
	private ExchangeRateService exchangeRateService;

	
	@GetMapping("/tc")
	public String getTC() {
		return exchangeRateService.getExchangeRate();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> BuscarById(@PathVariable String id) {
		MaeCompania compania= null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			compania = maeCompaniaService.BuscarById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		if(compania.getId() == null) {
			response.put("mensaje", "La empresa : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MaeCompania>(compania,HttpStatus.OK);		
	}
	

	
	@GetMapping("/listausu/{usuario}")
	public ResponseEntity<?> BuscarAllByUsuario(@PathVariable String usuario){
		List<MaeCompania> compania= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			compania = maeCompaniaService.BuscarAllByUsuario(usuario);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		if(compania.size() <=0) {
			response.put("mensaje", "No existen ninguna empresa asociada al usuario!");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}		
		return new ResponseEntity<List<MaeCompania>>(compania,HttpStatus.OK);
	}		


	@GetMapping("/all")
	public ResponseEntity<?> BuscarAll(){
		List<MaeCompania> compania= null;
		Map<String, Object> response = new HashMap<>();		
		
		try {
			compania = maeCompaniaService.BuscarAll();
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		if(compania.size() <=0) {
			response.put("mensaje", "No existen ninguna empresa asociada al usuario!");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}		
		return new ResponseEntity<List<MaeCompania>>(compania,HttpStatus.OK);
	}

	@GetMapping("/{id}/logo")
	public ResponseEntity<?> BuscarByIdLogo(@PathVariable String id) throws FileNotFoundException, IOException {
		MaeCompania compania= null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			compania = maeCompaniaService.BuscarById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		if(compania.getId() == null) {
			response.put("mensaje", "La empresa : ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}

		byte[] logo = compania.getBmplogo();
//		String rutaDestino = "dd";

		String rutaDestino = "D:\\Desarrollo\\Spring5\\logo1.jpg";

//		 if (logo != null && logo.length > 0) {
		if (logo != null ) {
			/*
			ByteArrayInputStream bais = new ByteArrayInputStream(logo);
        
			// Usar ImageIO para leer los datos binarios y convertirlos en una imagen
			BufferedImage bufferedImage = ImageIO.read(bais);
			
			// Guardar la imagen como JPG en la ruta de destino
			File file = new File(rutaDestino);
			ImageIO.write(bufferedImage, "jpg", file);
			*/
			
            // Crear el archivo JPG en la ruta de destino especificada
            //File file = new File(rutaDestino);
            try (FileOutputStream fos = new FileOutputStream(rutaDestino)) {
                fos.write(logo);
            }
			

            System.out.println("Imagen guardada en: " + rutaDestino);
        } else {
            throw new IllegalArgumentException("No se encontraron datos de imagen");
        }

//		byte[] logo = compania.getBmplogo();

		//BufferedImage imagen = null;
		/*
		try (InputStream inputStream = new ByteArrayInputStream(logo)) {
				 imagen = ImageIO.read(inputStream);
					*/
				/*
				// Aquí puedes hacer lo que quieras con la imagen, como guardarla en un archivo
				File archivoImagen = new File("imagen.jpg");
				ImageIO.write(imagen, "jpg", archivoImagen);
				*/
			/*} catch (IOException e) {
				
			}
			*/
			/*
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "image/jpeg"); // O el tipo de imagen adecuado
	
			return ResponseEntity.ok()
					.headers(headers)
					.body(logo);
			*/




		return new ResponseEntity<MaeCompania>(compania,HttpStatus.OK);		
	}

	
}
