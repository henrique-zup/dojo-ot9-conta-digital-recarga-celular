package br.com.zup.recargacelular.recarga;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.math.*;

import static org.junit.jupiter.api.Assertions.*;

class OperadoraTest {

    @ParameterizedTest
    @ValueSource(strings = { "10", "15", "20", "40", "50", "60", "100" })
    void deveRetornarTrueQuandoValoresDeRecargaForemValidos(String valor) {
        var valorRecarga = new BigDecimal(valor);
        var recargaValida = Operadora.validarValor(valorRecarga);

        assertTrue(recargaValida);
    }

    @ParameterizedTest
    @ValueSource(strings = { "9.9", "10.1" })
    void deveRetornarFalseQuandoValoresDeRecargaForemInvalidos(String valor) {
        var valorRecarga = new BigDecimal(valor);
        var recargaValida = Operadora.validarValor(valorRecarga);

        assertFalse(recargaValida);
    }

}