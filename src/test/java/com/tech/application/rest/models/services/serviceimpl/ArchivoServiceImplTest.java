package com.tech.application.rest.models.services.serviceimpl;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.test.util.ReflectionTestUtils;

public class ArchivoServiceImplTest {

    @TempDir Path carpeta;
    private ArchivoServiceImpl servicio;
    private static final String RUC = "20123456789";

    @BeforeEach
    void setUp() {
        servicio = new ArchivoServiceImpl();
        ReflectionTestUtils.setField(servicio, "rutaImagenes", carpeta.toString());
    }

    private void crear(String nombre) throws IOException {
        Files.createFile(carpeta.resolve(nombre));
    }

    @Test
    void logoJpg_existe_devuelveRutaCompleta() throws IOException {
        crear(RUC + "_logo.jpg");
        assertThat(servicio.ObtieneRutaImagen("LOGO", "COMPLETA", RUC))
            .isEqualTo(carpeta + File.separator + RUC + "_logo.jpg");
    }

    @Test
    void soloExisteFirmaPng_devuelvePng() throws IOException {
        crear(RUC + "_firma.png");
        assertThat(servicio.ObtieneRutaImagen("FIRMA", "ARCHIVO", RUC))
            .isEqualTo(RUC + "_firma.png");
    }

    @Test
    void sinImagen_devuelveLogoEnBlanco() {
        assertThat(servicio.ObtieneRutaImagen("LOGO", "ARCHIVO", RUC))
            .isEqualTo("logoblanco.jpg");
    }
}
