package br.com.zup.recargacelular.recarga;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class NovaRecargaRequest {
	
	@NotBlank
	@Pattern(regexp = "^\\(?[1-9]{2}\\)?[9]{0,1}[6-9]{1}[0-9]{7}$", message = "Formato inválido.")
	private String numeroCelular;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Operadora operadora;
	
	private BigDecimal valor;

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
