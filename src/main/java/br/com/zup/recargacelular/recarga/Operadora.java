package br.com.zup.recargacelular.recarga;

import java.math.*;
import java.util.*;

public enum Operadora {

	CLARO,
	TIM,
	VIVO,
	OI;

	public static Operadora buscar(String operadora) {
		return Arrays.stream(Operadora.values())
				.filter(e -> e.name().equals(operadora))
				.findFirst()
				.orElse(null);
	}

	private static final Set<BigDecimal> VALORES_ACEITOS = Set.of(
		new BigDecimal("10"),
		new BigDecimal("15"),
		new BigDecimal("20"),
		new BigDecimal("40"),
		new BigDecimal("50"),
		new BigDecimal("60"),
		new BigDecimal("100")
	);
	
	public static boolean validarValor(BigDecimal valor) {
		return VALORES_ACEITOS.contains(valor);
	}
}
