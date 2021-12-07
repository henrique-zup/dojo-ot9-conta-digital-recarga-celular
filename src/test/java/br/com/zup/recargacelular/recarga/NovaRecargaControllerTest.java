package br.com.zup.recargacelular.recarga;

import com.google.gson.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;

import java.math.*;

@SpringBootTest
@AutoConfigureMockMvc
class NovaRecargaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private RecargaService service;

    private final String uri = "/nova-recarga";

    @ParameterizedTest
    @ValueSource(strings = { "TIM", "CLARO", "VIVO", "OI" })
    void deveRetornarStatusCodeOkQuandoNovaRecargaRequestForValida(String operadora) throws Exception {
        var request = new NovaRecargaRequest(
                "(11)977208079",
                operadora,
                new BigDecimal("10")
        );

        Mockito.when(service.realizarRecarga(Mockito.any())).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request))
        ).andExpect(MockMvcResultMatchers.status().is(200));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "119230141", "0713281381", "82-413958251" })
    void deveRetornarStatusCodeBadRequestQuandoNumeroCelularDaNovaRecargaRequestForInvalido(String celular) throws Exception {
        var request = new NovaRecargaRequest(
                celular,
                "CLARO",
                new BigDecimal("10")
        );

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request))
        ).andExpect(MockMvcResultMatchers.status().is(400));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "NEXTEL", "ORELHAO", "DISK_GRATIS" })
    void deveRetornarStatusCodeBadRequestQuandoOperadoraNovaRecargaRequestForInvalido(String operadora) throws Exception {
        var request = new NovaRecargaRequest(
                "(11)977208079",
                operadora,
                new BigDecimal("10")
        );

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request))
        ).andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void deveRetornarStautsCodeBadRequestQuandoValorDaNovaRecargaRequestForNegativo() throws Exception {
        var request = new NovaRecargaRequest(
                "(11)977208079",
                "CLARO",
                new BigDecimal("-1")
        );

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request))
        ).andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void deveRetornarStautsCodeBadRequestQuandoValorDaNovaRecargaRequestForNulo() throws Exception {
        var request = new NovaRecargaRequest(
                "(11)977208079",
                "CLARO",
                null
        );

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request))
        ).andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void deveRetornarStatusCodeServiceUnavailableQuandoRecargaServiceEstiverIndisponivel() throws Exception {
        var request = new NovaRecargaRequest(
                "(11)977208079",
                "TIM",
                new BigDecimal("10")
        );

        Mockito.when(service.realizarRecarga(Mockito.any())).thenReturn(ResponseEntity.internalServerError().build());

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request))
        ).andExpect(MockMvcResultMatchers.status().is(503));
    }

}