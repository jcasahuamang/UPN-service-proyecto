package com.tech.application.rest.controllers;

//import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.lang.Object;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.application.rest.models.entity.shared.MenuModulo;
import com.tech.application.rest.models.entity.shared.SubMenuModulo;
import com.tech.application.rest.models.services.shared.IMenuModuloService;
import com.tech.application.rest.models.services.shared.ISubMenuModuloService;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/shared")
public class SharedController {

	@Autowired
	private IMenuModuloService menuModuloService;
	
	@Autowired
	private ISubMenuModuloService subMenuModuloService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/menumodulos/{usuario}")
	public ResponseEntity<MenuModulo> getModuloUsuario(@PathVariable String usuario){
		List<MenuModulo> menuModulo = menuModuloService.execProcGetModuloUsuario(usuario);
//		return menuModuloService.execProcGetModuloUsuario(usuario);
		return new ResponseEntity(menuModulo,HttpStatus.OK);
	}
	 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("submenumodulos/{usuario}/{id_modulo}/{id_funcion_sup}")
	public ResponseEntity<SubMenuModulo> getSubModuloUsuario(@PathVariable String usuario,@PathVariable Integer id_modulo,@PathVariable Integer id_funcion_sup){
		List<SubMenuModulo> subMenuModulo = subMenuModuloService.execProcGetSubModuloUsuario(usuario,id_modulo,id_funcion_sup);

		return new ResponseEntity(subMenuModulo,HttpStatus.OK);
	}
	
	@GetMapping("submenumodulos/list/{usuario}/{desurl}")
	public Boolean getSubUrlUsuario(@PathVariable String usuario,@PathVariable String desurl){
		List<SubMenuModulo> subMenuModulo = subMenuModuloService.execProcGetSubUrlUsuario(usuario,desurl);

		if(subMenuModulo.size() > 0) {
			//1L
			return true;
		}else {
			//0L
			return false;
		}
	}
	
}
