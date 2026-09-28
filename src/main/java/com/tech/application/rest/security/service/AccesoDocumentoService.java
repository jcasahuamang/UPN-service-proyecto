package com.tech.application.rest.security.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tech.application.rest.models.entity.IDatosPersonal;
import com.tech.application.rest.models.services.service.IPlaPersonalService;
import com.tech.application.rest.security.entity.UsuarioPrincipal;

/**
 * INS-02: verifica que los datos del documento solicitado pertenezcan
 * al usuario autenticado con el token JWT.
 */
@Service 
public class AccesoDocumentoService {

    @Autowired
    private IPlaPersonalService plaPersonalService;

    public boolean esDelUsuarioAutenticado(String codempresa, String codpersonal,
                                           String codusuario, String docidentidad) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof UsuarioPrincipal)) {
            return false;
        }
        UsuarioPrincipal usuario = (UsuarioPrincipal) auth.getPrincipal();

        if (!Objects.equals(limpio(usuario.getCodEmpresa()), limpio(codempresa))
                || !Objects.equals(limpio(usuario.getCodPersonal()), limpio(codpersonal))) {
            return false;
        }
        if (codusuario != null && !Objects.equals(usuario.getUsername(), codusuario)) {
            return false;
        }
        if (docidentidad != null) {
            IDatosPersonal datos = plaPersonalService.ObtenerDatoPersonal(
                    usuario.getCodEmpresa(), usuario.getCodPersonal(), usuario.getUsername());
            return datos != null
                    && Objects.equals(limpio(datos.getNumdocidentidad()), limpio(docidentidad));
        }
        return true;
    }

    // Quita espacios que pueden venir de columnas CHAR de SQL Server
    private static String limpio(String valor) {
        return valor == null ? null : valor.trim();
    }
}
