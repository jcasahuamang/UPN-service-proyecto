package com.tech.application.rest.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioPrincipal implements UserDetails {
	

	private String nombreUsuario;
	private String desUsuario;
	private String password;

	private String email;	
	private String estado;

	private String codEmpresa;
	private String codPersonal;
	private String admLevel;

	private Collection<? extends GrantedAuthority> authorities;

	public UsuarioPrincipal(
				String nombreUsuario,String desUsuario,
				String password, String email,
				String estado,String codEmpresa,String codPersonal,
				String admLevel,
			Collection<? extends GrantedAuthority> authorities) {

				this.nombreUsuario = nombreUsuario;
				this.desUsuario = desUsuario;
				this.password = password;
				this.email = email;
				this.estado = estado;
				this.codEmpresa = codEmpresa;
				this.codPersonal = codPersonal;
				this.admLevel = admLevel;

		this.authorities = authorities;
	}

	public static UsuarioPrincipal build(Usuario usuario) {
		
		List<GrantedAuthority> authorities = 
				usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
						.getRolNombre().name())).collect(Collectors.toList());
		
			return new UsuarioPrincipal(usuario.getNombreUsuario(),
										usuario.getDesUsuario(),
										usuario.getPassword(),
										usuario.getEmail(),
										usuario.getEstado(),
										usuario.getCodEmpresa(),
										usuario.getCodPersonal(),
										usuario.getAdmLevel(),
										authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//  Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		//  Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		//  Auto-generated method stub
		return nombreUsuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		//  Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		//  Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		//  Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		//  Auto-generated method stub
		return true;
	}

	public String getDesUsuario() {
		return desUsuario;
	}

	public String getEmail() {
		return email;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public String getCodEmpresa() {
		return codEmpresa;
	}

	public String getCodPersonal() {
		return codPersonal;
	}

	public String getAdmLevel() {
		return admLevel;
	}
}
