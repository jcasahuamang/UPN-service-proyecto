package com.tech.application.rest.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.tech.application.rest.models.entity.RepCertificado5ta;
import com.tech.application.rest.models.services.service.IArchivoService;
import com.tech.application.rest.models.services.service.IRepDocumentoPersonalService;

@ExtendWith(MockitoExtension.class)
public class RepDocumentoPersonalControllerQuintaTest {

    // Datos de prueba. El certificado de quinta es anual: el frontend siempre envía mes 12
    private static final String EMP = "0001", ANO = "2025", MES = "12", VER = "001",
            PER = "P001", USU = "jperez", DNI = "12345678", RUC = "20123456789";
    private static final String URL_VALIDA =
            "/personaldoc/valida/0001/2025/12/001/P001/jperez/12345678/5TA";
    private static final String URL_DATA =
            "/personaldoc/certificadoqta/data/0001/2025/12/P001/jperez/12345678";
    private static final String URL_PDF =
            "/personaldoc/certificadoqta/pdf/0001/2025/12/P001/jperez/12345678";

    @Mock private IRepDocumentoPersonalService servicio;   // simula la BD
    @Mock private IArchivoService archivoService;          // simula logo/firma
    @InjectMocks private RepDocumentoPersonalController controller;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    // ---------- Validación del periodo (tipo de documento 5TA) ----------

    @ParameterizedTest(name = "el procedimiento devuelve {0}")
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("CP-05/06: /valida con tipo 5TA devuelve el código del procedimiento")
    void validaQuinta_devuelveElCodigoDelProcedimiento(int codigo) throws Exception {
        when(servicio.ValidaVisualizacion(EMP, ANO, MES, VER, PER, USU, DNI, "5TA")).thenReturn(codigo);

        mvc.perform(get(URL_VALIDA).accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content().string(String.valueOf(codigo)));
    }

    // ---------- Datos del certificado de quinta ----------

    @Test
    @DisplayName("CP-05: /certificadoqta/data devuelve la lista del certificado")
    void quintaData_conDatos_responde200() throws Exception {
        when(servicio.execProcCertificado5ta(EMP, ANO, MES, PER, USU, DNI))
            .thenReturn(Collections.singletonList(new RepCertificado5ta()));

        mvc.perform(get(URL_DATA).accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$").isArray())
           .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    @DisplayName("/certificadoqta/data responde 500 si falla la BD")
    void quintaData_siFallaLaBD_responde500() throws Exception {
        when(servicio.execProcCertificado5ta(anyString(), anyString(), anyString(),
                anyString(), anyString(), anyString()))
            .thenThrow(new DataAccessResourceFailureException("BD no disponible"));

        mvc.perform(get(URL_DATA).accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isInternalServerError())
           .andExpect(jsonPath("$.mensaje").value("Error al realizar la consulta en la base de datos"))
           .andExpect(jsonPath("$.error").exists());   // INS-06
    }

    // ---------- Generación del PDF del certificado de quinta ----------

    @Test
    @DisplayName("INS-03: sin datos, el PDF de quinta falla con IndexOutOfBoundsException")
    void quintaPdf_sinDatos_lanzaExcepcion() {
        when(servicio.execProcCertificado5ta(anyString(), anyString(), anyString(),
                anyString(), anyString(), anyString()))
            .thenReturn(Collections.emptyList());

        Exception ex = assertThrows(Exception.class, () -> mvc.perform(get(URL_PDF)));
        assertThat(ex).hasRootCauseInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("CP-05: /certificadoqta/pdf genera un PDF válido")
    void quintaPdf_conDatos_generaPdf() throws Exception {
        RepCertificado5ta fila = new RepCertificado5ta();
        fila.setC_ruc_empresa(RUC);                   // se usa para buscar logo y firma
        when(servicio.execProcCertificado5ta(EMP, ANO, MES, PER, USU, DNI))
            .thenReturn(Collections.singletonList(fila));

        String imagen = new ClassPathResource("imagenes/prueba.jpg").getFile().getAbsolutePath();
        when(archivoService.ObtieneRutaImagen(anyString(), eq("COMPLETA"), eq(RUC)))
            .thenReturn(imagen);

        MvcResult r = mvc.perform(get(URL_PDF))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_PDF))
            .andReturn();

        byte[] pdf = r.getResponse().getContentAsByteArray();
        assertThat(pdf.length).isGreaterThan(0);
        assertThat(new String(pdf, 0, 4)).isEqualTo("%PDF");
        verify(archivoService, times(2)).ObtieneRutaImagen(anyString(), eq("COMPLETA"), anyString());
    }
}
