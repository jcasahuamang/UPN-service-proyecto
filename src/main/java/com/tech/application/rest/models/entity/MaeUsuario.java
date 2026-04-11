package com.tech.application.rest.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MAE_USUARIO")
public class MaeUsuario {
	
	@Column(name="id_cliente",nullable=false)		
	private Integer idCliente;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario",nullable=false)	
	private Integer id;
	
	@Column(name="usuario",nullable=false,unique=true,length=50)			
	private String usuario;
	
	
	@Column(name="clave",nullable=false,length=250)				
	private String clave;
	
	@Column(name="correo",nullable=true,length=250)			
	private String email;

	@Column(name="primer_nombre",nullable=true,length=250)	
	private String primerNombre;
	
	@Column(name="segundo_nombre",nullable=true,length=250)		
	private String segundoNombre;
	
	@Column(name="apellidos",nullable=true,length=250)			
	private String apellidos;

	@Column(name="tipo",nullable=true)		
	private Integer tipo;

	@Column(name="estado",nullable=true)			
	private Integer estado;

	public MaeUsuario() {

	}
	
	
	public MaeUsuario(Integer idCliente, String usuario, String clave, String email, String primerNombre,
			String segundoNombre, String apellidos, Integer tipo, Integer estado) {
		this.idCliente = idCliente;
		this.usuario = usuario;
		this.clave = clave;
		this.email = email;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.apellidos = apellidos;
		this.tipo = tipo;
		this.estado = estado;
	}



	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	
	

}
