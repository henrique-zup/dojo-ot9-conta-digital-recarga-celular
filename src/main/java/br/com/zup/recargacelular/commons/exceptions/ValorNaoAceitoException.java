package br.com.zup.recargacelular.commons.exceptions;

import java.math.BigDecimal;

public class ValorNaoAceitoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private BigDecimal valor;

	public ValorNaoAceitoException(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValor() {
		return valor;
	}
}
