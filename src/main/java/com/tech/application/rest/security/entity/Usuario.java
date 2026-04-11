package com.tech.application.rest.security.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
@Table(name="MAE_USUARIO")
public class Usuario {

	@Id
	@Column(name="COD_USUARIO",nullable=false,unique=true,length=30)		
	private String nombreUsuario;

	@Column(name="NOM_USUARIO",nullable=false,length=100)			
	private String desUsuario;


	@Column(name="DES_PASSWORD",nullable=false,length=150)			
	private String password;

	@Column(name="USUARIO_MAIL",nullable=true,length=250)				
	private String email;	


	@Column(name="IND_BAJA",nullable=true,length=1)			
	private String estado;

	@Column(name="COD_EMPRESA",nullable=true,length=4)			
	private String codEmpresa;

	@Column(name="COD_PERSONAL",nullable=true,length=20)			
	private String codPersonal;

	@Column(name="ADM_LEVEL",nullable=true,length=1)			
	private String admLevel;


	/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario",nullable=false)	
	private Long id;
	
	*/

	@NotNull
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="usuario_rol",joinColumns = @JoinColumn(name="usuario_id"),
				inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles = new HashSet<>();

	
	public Usuario() {
		
	}

	

	public Usuario(@NotNull String nombreUsuario,@NotNull String desUsuario,
				@NotNull String password,@NotNull String email,
				String estado,String codEmpresa,String codPersonal,
				String admLevel) {
		
				this.nombreUsuario = nombreUsuario;
				this.desUsuario = desUsuario;
				this.password = password;
				this.email = email;
				this.estado = estado;
				this.codEmpresa = codEmpresa;
				this.codPersonal = codPersonal;
				this.admLevel = admLevel;
				
	}
	

	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getDesUsuario() {
		return desUsuario;
	}


	public void setDesUsuario(String desUsuario) {
		this.desUsuario = desUsuario;
	}



	public String getPassword() {
		
		String vdesencriptado = "";

		vdesencriptado = desencripta(password);
//		System.out.println("DESENCRIPTADO: "+vdesencriptado);

		return new BCryptPasswordEncoder().encode(vdesencriptado);

		//return password;
		/*
		String prueba = "jsaavedra";
		String vencriptado = "";
		String vdesencriptado = "";

		vencriptado = encripta(prueba);
		System.out.println("ENCRIPTADO: "+vencriptado);


		vdesencriptado = desencripta(vencriptado);
		System.out.println("DESENCRIPTADO: "+vdesencriptado);

		return new BCryptPasswordEncoder().encode(vdesencriptado);
		*/
	}


	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
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

	public String getAdmLevel() {
		return admLevel;
	}


	public void setAdmLevel(String admLevel) {
		this.admLevel = admLevel;
	}


	public Set<Rol> getRoles() {
		return roles;
	}


	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	
	public String desencripta(String as_cadena_ing) {
        int il_longi, il_count,  il_base;
        String vl_cadena_conv = "", ls_cadena_ing = "", as_cadena_dev = "";

        // Paso 1: Construcción de ls_cadena_ing
        il_longi = as_cadena_ing.length();
        il_count = 0;
        
        // Tomar bloques de 3 caracteres
        while (il_count < il_longi) {
            // Obtener una subcadena de 3 caracteres
            String subcadena = as_cadena_ing.substring(il_count, Math.min(il_count + 3, il_longi));
            // Convertir la subcadena en su valor ASCII y agregarlo a ls_cadena_ing
            ls_cadena_ing += (char) Integer.parseInt(subcadena);
            il_count += 3;
        }

        // Paso 2: Calcular il_base (la mitad del valor ASCII del último carácter de ls_cadena_ing)
        il_base = (int) ls_cadena_ing.charAt(ls_cadena_ing.length() - 1) / 2;

        // Paso 3: Crear vl_cadena_conv (extraer la subcadena sin el primer y último carácter de ls_cadena_ing)
        vl_cadena_conv = ls_cadena_ing.substring(1, ls_cadena_ing.length() - 1);
		
        // Paso 4: Modificar vl_cadena_conv eliminando una parte al inicio y al final
        il_longi = vl_cadena_conv.length() / 4;

//        vl_cadena_conv = vl_cadena_conv.substring(il_longi, vl_cadena_conv.length() - (2 * il_longi));
		vl_cadena_conv = vl_cadena_conv.substring(il_longi, vl_cadena_conv.length() - il_longi);

        // Paso 5: Construir as_cadena_dev (ajustar cada carácter por il_base)
        il_longi = vl_cadena_conv.length();
        il_count = 0;
        

        while (il_count < il_longi) {
            // Obtener el valor ASCII del carácter y ajustarlo
            as_cadena_dev += (char) (vl_cadena_conv.charAt(il_count) - il_base);
            il_count++;
        }

        return as_cadena_dev;
	}
	
	public String encripta(String asCadenaIng) {

		int ilLongi, ilCount, ilSuma, ilBase;
        String vlCadenaConv = "",asCadenaDev = "",lsCadenaDev = "",lsCadenaDevF = "";

        // Primer paso: Crear vlCadenaConv con las subcadenas
        ilLongi = asCadenaIng.length() / 2;  // Equivalente a truncate(len(asCadenaIng)/2, 0)
        vlCadenaConv = asCadenaIng.substring(asCadenaIng.length() - ilLongi) + asCadenaIng
                + asCadenaIng.substring(0, ilLongi);

        // Segundo paso: Sumar los valores ASCII de la cadena
        ilLongi = vlCadenaConv.length();
        ilCount = 0;
        ilSuma = 0;
		
        while (ilCount < ilLongi) {
            ilSuma += vlCadenaConv.charAt(ilCount); // Usamos charAt() para obtener el código ASCII
            ilCount++;
        }
		
        // Tercer paso: Calcular el valor base
        ilBase = ilSuma / ilLongi;

        // Cuarto paso: Generar asCadenaDev
        ilCount = 0;
        while (ilCount < ilLongi) {
            asCadenaDev += (char) (vlCadenaConv.charAt(ilCount) + ilBase);
            ilCount++;
        }

        // Generar lsCadenaDev
        lsCadenaDev = (char) (ilBase - 15) + asCadenaDev + (char) (2 * ilBase);

        // Quinto paso: Crear lsCadenaDevF con los valores ASCII formateados
        ilLongi = lsCadenaDev.length();
        ilCount = 0;
        lsCadenaDevF = "";

        while (ilCount < ilLongi) {
            lsCadenaDevF += String.format("%03d", (int) lsCadenaDev.charAt(ilCount));  // Formateamos a 3 dígitos
            ilCount++;
        }

        // Asignamos el valor final a asCadenaDev
        asCadenaDev = lsCadenaDevF;

        return asCadenaDev;
	}
	
}
