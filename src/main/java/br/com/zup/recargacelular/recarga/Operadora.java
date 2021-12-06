package br.com.zup.recargacelular.recarga;

import java.math.BigDecimal;
import java.util.Set;

public enum Operadora {
	CLARO,
	TIM,
	VIVO,
	OI;
	
	private Set<BigDecimal> VALORES_ACEITOS = Set.of(
			new BigDecimal("10"),
			new BigDecimal("15"),
			new BigDecimal("20"),
			new BigDecimal("40"),
			new BigDecimal("50"),
			new BigDecimal("60"),
			new BigDecimal("100")
	);
	
	public boolean validarValor(BigDecimal valor) {
		return VALORES_ACEITOS.contains(valor);
	}
}
