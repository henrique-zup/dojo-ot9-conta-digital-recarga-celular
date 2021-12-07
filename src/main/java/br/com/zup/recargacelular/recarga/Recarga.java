package br.com.zup.recargacelular.recarga;

import br.com.zup.recargacelular.commons.exceptions.*;

import java.math.*;

public class Recarga {

    private String numeroCelular;
    private Operadora operadora;
    private BigDecimal valor;

    private Recarga(String numeroCelular, Operadora operadora, BigDecimal valor) {
        if (operadora == null)
            throw new OperadoraInvalidaException();

        if (!Operadora.validarValor(valor))
            throw new ValorNaoAceitoException(valor);

        this.numeroCelular = numeroCelular;
        this.operadora = operadora;
        this.valor = valor;
    }

    public Recarga(NovaRecargaRequest request) {
        this(request.getNumeroCelular(), Operadora.buscar(request.getOperadora()), request.getValor());
    }

    public RecargaResponse toResponse(String mensagem) {
        return new RecargaResponse(this, mensagem);
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public Operadora getOperadora() {
        return operadora;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
