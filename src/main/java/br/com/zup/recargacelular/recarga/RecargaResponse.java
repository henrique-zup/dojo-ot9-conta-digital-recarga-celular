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

	public RecargaResponse(NovaRecargaRequest request, String mensagem) {
		this.numeroCelular = request.getNumeroCelular();
		this.operadora = request.getOperadora();
		this.valor = request.getValor();
		this.mensagem = mensagem;
	}

}
