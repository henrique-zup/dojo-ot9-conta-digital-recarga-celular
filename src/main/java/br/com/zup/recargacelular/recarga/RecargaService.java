package br.com.zup.recargacelular.recarga;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RecargaService {
	
	public ResponseEntity<?> realizarRecarga(NovaRecargaRequest request) {
		
		Operadora operadora = request.getOperadora();
		
		ResponseEntity<?> resposta = ResponseEntity.ok().build();
		
		switch(operadora) {
			case TIM:
				if (request.getNumeroCelular().endsWith("0"))
					resposta = ResponseEntity.internalServerError().build();
				break;
			case OI:
				if (request.getNumeroCelular().endsWith("1"))
					resposta = ResponseEntity.internalServerError().build();
				break;
			default:
				resposta = ResponseEntity.ok().build();
		}
		
		return resposta;
	}

}
