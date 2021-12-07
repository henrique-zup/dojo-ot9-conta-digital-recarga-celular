package br.com.zup.recargacelular.recarga;

import br.com.zup.recargacelular.commons.exceptions.*;
import org.junit.jupiter.api.*;

import java.math.*;

import static org.junit.jupiter.api.Assertions.*;

class RecargaTest {

    @Test
    void deveInstanciarQuandoDadosDeNovaRecargaRequestForemValidos() {
        var request = new NovaRecargaRequest("(11)977208079", "CLARO", BigDecimal.TEN);
        var recarga = new Recarga(request);

        assertNotNull(recarga);
    }

    @Test
    void deveLancarValorNaoAceitoExceptionQuandoValorDaRecargaNaoForAceito() {
        var request = new NovaRecargaRequest("(11)977208079", "CLARO", new BigDecimal("9.9"));

        assertThrows(
            ValorNaoAceitoException.class, () -> new Recarga(request)
        );
    }

    @Test
    void deveLancarOperadoraInvalidaExceptionQuandoOperadoraNaoExistir() {
        var request = new NovaRecargaRequest("(11)977208079", "DISKGRATIS", new BigDecimal("10"));

        assertThrows(
                OperadoraInvalidaException.class, () -> new Recarga(request)
        );
    }

}