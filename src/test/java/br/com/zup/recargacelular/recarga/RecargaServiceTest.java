package br.com.zup.recargacelular.recarga;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;

import java.math.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecargaServiceTest {

    @Autowired
    private RecargaService service;

    @MockBean
    private RecargaRepository repository;

    @Test
    void deveRetornarResponseEntityOkQuandoServicoDisponivel() {
        var request = new NovaRecargaRequest(
                "(11)977208079",
                "OI",
                new BigDecimal("10")
        );
        var recarga = new Recarga(request);

        Mockito.when(repository.save(Mockito.any(Recarga.class))).thenReturn(Mockito.mock(Recarga.class));
        var statusCodeResponse = service.verificarDisponibilidade(recarga).getStatusCode();

        assertEquals(HttpStatus.OK, statusCodeResponse);
    }

    @ParameterizedTest
    @CsvSource({"TIM,(11)977208070", "OI,(11)977208071", "VIVO,(11)977208072", "CLARO,(11)977208073" })
    void deveRetornarResponseEntityInternalServerErrorQuandoServicoIndisponivel(String operadora, String celular) {
        var request = new NovaRecargaRequest(
                celular,
                operadora,
                new BigDecimal("10")
        );
        var recarga = new Recarga(request);
        var statusCodeResponse = service.verificarDisponibilidade(recarga).getStatusCode();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, statusCodeResponse);
    }

}