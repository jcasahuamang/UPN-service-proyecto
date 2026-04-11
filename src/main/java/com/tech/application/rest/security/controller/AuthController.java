package com.tech.application.rest.security.controller;


import java.util.HashMap;
//import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

//import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.application.rest.models.entity.MaeCompania;
import com.tech.application.rest.models.services.service.IMaeCompaniaService;
import com.tech.application.rest.security.dto.JwtDto;
import com.tech.application.rest.security.dto.LoginUsuario;
//import com.tech.application.rest.security.dto.NuevoUsuario;
//import com.tech.application.rest.security.entity.Rol;
import com.tech.application.rest.security.entity.Usuario;
//import com.tech.application.rest.security.enums.RolNombre;
import com.tech.application.rest.security.jwt.JwtProvider;
import com.tech.application.rest.security.service.RolService;
import com.tech.application.rest.security.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins= "*")
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager; 
	
	@Autowired
	IMaeCompaniaService  maeCompaniaService;

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	RolService rolService;
	
	@Autowired
	JwtProvider jwtProvider;
	
	/*
	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario,BindingResult bindingResult){
		Map<String, Object> response = new HashMap<>();
		if (bindingResult.hasErrors()) {
			response.put("mensaje", "Campos mal puesto o email invalido");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}

		if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
			response.put("mensaje", "Ese nombre ya existe");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);			
		}

		if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
			response.put("mensaje", "Ese email ya esiste");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);						
		}
		
		Usuario usuario = new Usuario(nuevoUsuario.getPrimer_nombre(),
									nuevoUsuario.getSegundo_nombre(),
									nuevoUsuario.getApellidos(),
									nuevoUsuario.getNombreUsuario(),
									nuevoUsuario.getEmail(),
									passwordEncoder.encode( nuevoUsuario.getPassword()),
									nuevoUsuario.getId_cliente(),
									nuevoUsuario.getTipo(),
									nuevoUsuario.getEstado());
			
	Set<Rol> roles = new HashSet<>();
	roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
	if(nuevoUsuario.getRoles().contains("admin"))
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
	usuario.setRoles(roles);
	usuarioService.save(usuario);
	response.put("mensaje", "usuario creado");
	return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	*/

//	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario,BindingResult bindingResult){	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario,BindingResult bindingResult){
		Map<String, Object> response = new HashMap<>();
		if (bindingResult.hasErrors()) {
			response.put("mensaje", "Campos mal puesto");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
			
		
		Authentication authentication = 
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		Optional<Usuario> usuario = usuarioService.getByNombreUsuario(userDetails.getUsername());
		
		JwtDto jwtDto = new JwtDto(jwt,
								userDetails.getUsername(),
								usuario.get().getDesUsuario(),
								usuario.get().getAdmLevel(),
								usuario.get().getCodEmpresa(),
								usuario.get().getCodPersonal(),
								userDetails.getAuthorities());
		return new ResponseEntity<JwtDto>(jwtDto,HttpStatus.OK);
		
	}

	@GetMapping("/empresa/{id}")
	public ResponseEntity<?> BuscarById(@PathVariable String id) {
		MaeCompania compania= null;
		
			compania = maeCompaniaService.BuscarById(id);

		return new ResponseEntity<MaeCompania>(compania,HttpStatus.OK);		
	}

}
