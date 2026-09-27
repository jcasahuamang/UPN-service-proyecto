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

import com.tech.application.rest.models.entity.RepBoletaCts;
import com.tech.application.rest.models.services.service.IArchivoService;
import com.tech.application.rest.models.services.service.IRepDocumentoPersonalService;

@ExtendWith(MockitoExtension.class)
public class RepDocumentoPersonalControllerCtsTest {

    // Datos de prueba. La boleta CTS solo usa empresa, año, mes y código de personal
    private static final String EMP = "0001", ANO = "2026", MES = "05", VER = "001",
            PER = "P001", USU = "jperez", DNI = "12345678", RUC = "20123456789";
    private static final String URL_VALIDA =
            "/personaldoc/valida/0001/2026/05/001/P001/jperez/12345678/CTS";
    private static final String URL_DATA = "/personaldoc/boletacts/data/0001/2026/05/P001";
    private static final String URL_PDF  = "/personaldoc/boletacts/pdf/0001/2026/05/P001";

    @Mock private IRepDocumentoPersonalService servicio;   // simula la BD
    @Mock private IArchivoService archivoService;          // simula logo/firma
    @InjectMocks private RepDocumentoPersonalController controller;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    // ---------- Validación del periodo (tipo de documento CTS) ----------

    @ParameterizedTest(name = "el procedimiento devuelve {0}")
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("CP-03/04: /valida con tipo CTS devuelve el código del procedimiento")
    void validaCts_devuelveElCodigoDelProcedimiento(int codigo) throws Exception {
        when(servicio.ValidaVisualizacion(EMP, ANO, MES, VER, PER, USU, DNI, "CTS")).thenReturn(codigo);

        mvc.perform(get(URL_VALIDA).accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content().string(String.valueOf(codigo)));
    }

    // ---------- Datos de la boleta CTS ----------

    @Test
    @DisplayName("CP-03: /boletacts/data devuelve la lista de la boleta CTS")
    void ctsData_conDatos_responde200() throws Exception {
        when(servicio.execProcBoletaCts(EMP, ANO, MES, PER))
            .thenReturn(Collections.singletonList(new RepBoletaCts()));

        mvc.perform(get(URL_DATA).accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$").isArray())
           .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    @DisplayName("/boletacts/data responde 500 si falla la BD")
    void ctsData_siFallaLaBD_responde500() throws Exception {
        when(servicio.execProcBoletaCts(anyString(), anyString(), anyString(), anyString()))
            .thenThrow(new DataAccessResourceFailureException("BD no disponible"));

        mvc.perform(get(URL_DATA).accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isInternalServerError())
           .andExpect(jsonPath("$.mensaje").value("Error al realizar la consulta en la base de datos"))
           .andExpect(jsonPath("$.error").exists());   // INS-06
    }

    // ---------- Generación del PDF de la boleta CTS ----------

    @Test
    @DisplayName("INS-03: sin datos, el PDF CTS falla con IndexOutOfBoundsException")
    void ctsPdf_sinDatos_lanzaExcepcion() {
        when(servicio.execProcBoletaCts(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(Collections.emptyList());

        Exception ex = assertThrows(Exception.class, () -> mvc.perform(get(URL_PDF)));
        assertThat(ex).hasRootCauseInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("CP-03: /boletacts/pdf genera un PDF válido")
    void ctsPdf_conDatos_generaPdf() throws Exception {
        RepBoletaCts fila = new RepBoletaCts();
        fila.setNum_ruc_empresa(RUC);                 // se usa para buscar logo y firma
        when(servicio.execProcBoletaCts(EMP, ANO, MES, PER))
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
