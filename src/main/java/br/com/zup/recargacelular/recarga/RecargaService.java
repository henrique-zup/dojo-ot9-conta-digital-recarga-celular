package br.com.zup.recargacelular.recarga;

import org.springframework.http.*;
import org.springframework.stereotype.*;

@Service
public class RecargaService {
	
	public ResponseEntity<?> realizarRecarga(Recarga recarga) {
		Operadora operadora = recarga.getOperadora();
		ResponseEntity<?> resposta = ResponseEntity.ok().build();

		// Simulação de serviço indisponível
		switch(operadora) {
			case TIM:
				if (recarga.getNumeroCelular().endsWith("0"))
					resposta = ResponseEntity.internalServerError().build();
				break;
			case OI:
				if (recarga.getNumeroCelular().endsWith("1"))
					resposta = ResponseEntity.internalServerError().build();
				break;
			case VIVO:
				if (recarga.getNumeroCelular().endsWith("2"))
					resposta = ResponseEntity.internalServerError().build();
				break;
			case CLARO:
				if (recarga.getNumeroCelular().endsWith("3"))
					resposta = ResponseEntity.internalServerError().build();
				break;
		}
		
		return resposta;
	}

}
