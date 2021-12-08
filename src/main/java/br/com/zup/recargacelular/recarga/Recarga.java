package br.com.zup.recargacelular.recarga;

import br.com.zup.recargacelular.commons.exceptions.*;

import javax.persistence.*;
import java.math.*;

@Entity
public class Recarga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCelular;

    @Enumerated(EnumType.STRING)
    private Operadora operadora;

    private BigDecimal valor;

    private boolean efetuada = false;

    private Recarga() {}

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

    public void efetuarRecarga() {
        this.efetuada = true;
    }
}
