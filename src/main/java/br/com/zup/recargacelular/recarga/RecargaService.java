package br.com.zup.recargacelular.recarga;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

@Service
public class RecargaService {

	@Autowired
	private RecargaRepository repository;

	public ResponseEntity<?> verificarDisponibilidade(Recarga recarga) {
		var operadora = recarga.getOperadora();
		var resposta = ResponseEntity.ok().build();

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

		if (resposta.getStatusCode().equals(HttpStatus.OK))
			repository.save(recarga);

		return resposta;
	}

}
