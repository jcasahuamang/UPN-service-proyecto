package com.tech.application.rest.security.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tech.application.rest.models.entity.IDatosPersonal;
import com.tech.application.rest.models.services.service.IPlaPersonalService;
import com.tech.application.rest.security.entity.UsuarioPrincipal;

@ExtendWith(MockitoExtension.class)
public class AccesoDocumentoServiceTest {

    @Mock private IPlaPersonalService plaPersonalService;
    @Mock private IDatosPersonal datos;
    @InjectMocks private AccesoDocumentoService acceso;

    @BeforeEach
    void iniciarSesion() {
        // Simula lo que hace JwtTokenFilter: usuario jperez, empresa 0001, personal P001
        UsuarioPrincipal usuario = new UsuarioPrincipal("jperez", "Juan Perez", "clave",
                "jperez@correo.com", "A", "0001", "P001", "1", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList()));
    }

    @AfterEach
    void cerrarSesion() {
        SecurityContextHolder.clearContext();
    }

    @Test
    @DisplayName("Sin usuario autenticado se niega el acceso")
    void sinUsuarioAutenticado_niega() {
        SecurityContextHolder.clearContext();
        assertThat(acceso.esDelUsuarioAutenticado("0001", "P001", "jperez", null)).isFalse();
    }

    @Test
    @DisplayName("Documento de otra empresa: se niega")
    void otraEmpresa_niega() {
        assertThat(acceso.esDelUsuarioAutenticado("0002", "P001", "jperez", null)).isFalse();
    }

    @Test
    @DisplayName("Documento de otro trabajador: se niega")
    void otroTrabajador_niega() {
        assertThat(acceso.esDelUsuarioAutenticado("0001", "P999", "jperez", null)).isFalse();
    }

    @Test
    @DisplayName("Usuario de la URL distinto al del token: se niega")
    void otroUsuario_niega() {
        assertThat(acceso.esDelUsuarioAutenticado("0001", "P001", "otro", null)).isFalse();
    }

    @Test
    @DisplayName("Boleta CTS propia (sin usuario ni DNI): se permite sin consultar la BD")
    void mismoTrabajadorSinDni_permite() {
        assertThat(acceso.esDelUsuarioAutenticado("0001", "P001", null, null)).isTrue();
        verifyNoInteractions(plaPersonalService);
    }

    @Test
    @DisplayName("DNI del propio trabajador: se permite")
    void dniPropio_permite() {
        when(plaPersonalService.ObtenerDatoPersonal("0001", "P001", "jperez")).thenReturn(datos);
        when(datos.getNumdocidentidad()).thenReturn("12345678");

        assertThat(acceso.esDelUsuarioAutenticado("0001", "P001", "jperez", "12345678")).isTrue();
    }

    @Test
    @DisplayName("DNI de otra persona: se niega")
    void dniAjeno_niega() {
        when(plaPersonalService.ObtenerDatoPersonal("0001", "P001", "jperez")).thenReturn(datos);
        when(datos.getNumdocidentidad()).thenReturn("87654321");

        assertThat(acceso.esDelUsuarioAutenticado("0001", "P001", "jperez", "12345678")).isFalse();
    }
}
