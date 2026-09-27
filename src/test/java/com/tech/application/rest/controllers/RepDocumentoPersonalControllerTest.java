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

import com.tech.application.rest.models.entity.RepBoletaPago;
import com.tech.application.rest.models.services.service.IArchivoService;
import com.tech.application.rest.models.services.service.IRepDocumentoPersonalService;

@ExtendWith(MockitoExtension.class)
public class RepDocumentoPersonalControllerTest {

    // Datos de prueba (los mismos en todas las pruebas)
    private static final String EMP = "0001", ANO = "2026", MES = "08", VER = "001",
            PER = "P001", USU = "jperez", DNI = "12345678";
    private static final String URL_VALIDA =
            "/personaldoc/valida/0001/2026/08/001/P001/jperez/12345678/BOL";
    private static final String URL_DATA =
            "/personaldoc/boletapago/data/0001/2026/08/001/P001/jperez/12345678";
    private static final String URL_PDF =
            "/personaldoc/boletapago/pdf/0001/2026/08/001/P001/jperez/12345678";

    @Mock private IRepDocumentoPersonalService servicio;   // simula la BD
    @Mock private IArchivoService archivoService;          // simula logo/firma
    @InjectMocks private RepDocumentoPersonalController controller;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    // ---------- Paso 3: validación del periodo ----------

    @ParameterizedTest(name = "el procedimiento devuelve {0}")
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("CP-01/02: /valida devuelve el código del procedimiento")
    void valida_devuelveElCodigoDelProcedimiento(int codigo) throws Exception {
        when(servicio.ValidaVisualizacion(EMP, ANO, MES, VER, PER, USU, DNI, "BOL")).thenReturn(codigo);

        mvc.perform(get(URL_VALIDA).accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content().string(String.valueOf(codigo)));
    }

    @Test
    @DisplayName("INS-01: si la BD falla, /valida hoy devuelve 0 (permitir)")
    void valida_siFallaLaBD_devuelveCero() throws Exception {
        when(servicio.ValidaVisualizacion(anyString(), anyString(), anyString(), anyString(),
                anyString(), anyString(), anyString(), anyString()))
            .thenThrow(new DataAccessResourceFailureException("BD no disponible"));

        mvc.perform(get(URL_VALIDA).accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content().string("0"));   // tras corregir INS-01 esto debe cambiar
    }

    // ---------- Paso 4: datos de la boleta ----------

    @Test
    @DisplayName("CP-01: /boletapago/data devuelve la lista de la boleta")
    void boletaData_conDatos_responde200() throws Exception {
        when(servicio.execProcBoletaPago(EMP, ANO, MES, VER, PER, USU, DNI))
            .thenReturn(Collections.singletonList(new RepBoletaPago()));

        mvc.perform(get(URL_DATA).accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$").isArray())
           .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    @DisplayName("/boletapago/data responde 500 si falla la BD")
    void boletaData_siFallaLaBD_responde500() throws Exception {
        when(servicio.execProcBoletaPago(anyString(), anyString(), anyString(), anyString(),
                anyString(), anyString(), anyString()))
            .thenThrow(new DataAccessResourceFailureException("BD no disponible"));

        mvc.perform(get(URL_DATA).accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isInternalServerError())
           .andExpect(jsonPath("$.mensaje").value("Error al realizar la consulta en la base de datos"))
           .andExpect(jsonPath("$.error").exists());   // INS-06: el detalle técnico llega al cliente
    }

    // ---------- Paso 5: generación del PDF ----------

    @Test
    @DisplayName("INS-03: sin datos, el PDF falla con IndexOutOfBoundsException")
    void boletaPdf_sinDatos_lanzaExcepcion() {
        when(servicio.execProcBoletaPago(anyString(), anyString(), anyString(), anyString(),
                anyString(), anyString(), anyString()))
            .thenReturn(Collections.emptyList());

        Exception ex = assertThrows(Exception.class, () -> mvc.perform(get(URL_PDF)));
        assertThat(ex).hasRootCauseInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("CP-01: /boletapago/pdf genera un PDF válido")
    void boletaPdf_conDatos_generaPdf() throws Exception {
        RepBoletaPago fila = new RepBoletaPago();
        fila.setNUM_RUC_EMPRESA("20123456789");      // se usa para buscar logo y firma
        when(servicio.execProcBoletaPago(EMP, ANO, MES, VER, PER, USU, DNI))
            .thenReturn(Collections.singletonList(fila));

        String imagen = new ClassPathResource("imagenes/prueba.jpg").getFile().getAbsolutePath();
        when(archivoService.ObtieneRutaImagen(anyString(), eq("COMPLETA"), eq("20123456789")))
            .thenReturn(imagen);

        MvcResult r = mvc.perform(get(URL_PDF))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_PDF))
            .andReturn();

        byte[] pdf = r.getResponse().getContentAsByteArray();
        assertThat(pdf.length).isGreaterThan(0);
        assertThat(new String(pdf, 0, 4)).isEqualTo("%PDF");   // cabecera de todo PDF
        verify(archivoService, times(2)).ObtieneRutaImagen(anyString(), eq("COMPLETA"), anyString());
    }
}
