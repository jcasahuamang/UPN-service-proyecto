package com.tech.application.rest.security.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUsuario {
	@NotBlank
	private String nombreUsuario;	
	@NotBlank
	private String password;	
	
}
