package br.com.zup.recargacelular.recarga;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecargaResponse {
	
	@JsonProperty
	private String numeroCelular;
	
	@JsonProperty
	private Operadora operadora;
	
	@JsonProperty
	private BigDecimal valor;
	
	@JsonProperty
	private String mensagem;

	public RecargaResponse(Recarga recarga, String mensagem) {
		this.numeroCelular = recarga.getNumeroCelular();
		this.operadora = recarga.getOperadora();
		this.valor = recarga.getValor();
		this.mensagem = mensagem;
	}

}
