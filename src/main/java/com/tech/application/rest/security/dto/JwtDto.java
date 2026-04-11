package com.tech.application.rest.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtDto {

	private String token;
	private String bearer = "Bearer";
	private String nombreUsuario;
	private String nombreCompleto;

	private String admLevel;
	private String codEmpresa;
	private String codPersonal;

	private Collection<? extends GrantedAuthority> authorities;
	
	public JwtDto(String token, String nombreUsuario,String nombreCompleto,
			String admLevel,String codEmpresa,String codPersonal,
			Collection<? extends GrantedAuthority> authorities) {
		this.token = token;
		this.nombreUsuario = nombreUsuario;
		this.nombreCompleto = nombreCompleto;			
		this.admLevel = admLevel;
		this.codEmpresa = codEmpresa;
		this.codPersonal = codPersonal;
		/*
		if (segundoNombre.isEmpty()) {
			this.nombreCompleto = primerNombre.concat(" ").concat(apellidos);			
		}else {
			this.nombreCompleto = primerNombre.concat(" ").concat(segundoNombre).concat(" ").concat(apellidos);			
		}
		*/
		this.authorities = authorities;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBearer() {
		return bearer;
	}

	public void setBearer(String bearer) {
		this.bearer = bearer;
	}

		

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	

	public String getAdmLevel() {
		return admLevel;
	}

	public void setAdmLevel(String admLevel) {
		this.admLevel = admLevel;
	}

	public String getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public String getCodPersonal() {
		return codPersonal;
	}

	public void setCodPersonal(String codPersonal) {
		this.codPersonal = codPersonal;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	
}
