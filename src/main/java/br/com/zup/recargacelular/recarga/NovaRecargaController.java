package br.com.zup.recargacelular.recarga;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.recargacelular.commons.exceptions.ValorNaoAceitoException;

@RestController
@RequestMapping("/nova-recarga")
public class NovaRecargaController {
	
	private final String SERVICO_DISPONIVEL = "A recarga foi encaminhada com sucesso.";
	private final String SERVICO_INDISPONIVEL = "Serviço indisponível no momento.";
	
	@Autowired
	private RecargaService recargaService;
	
	@PostMapping
	public ResponseEntity<?> recarregar(@RequestBody @Valid NovaRecargaRequest request) {
		var recarga = new Recarga(request);

		var disponibilidadeService = recargaService.verificarDisponibilidade(recarga);
		
		return disponibilidadeService.getStatusCode().equals(HttpStatus.OK)
				? ResponseEntity.ok(recarga.toResponse(SERVICO_DISPONIVEL))
				: ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(recarga.toResponse(SERVICO_INDISPONIVEL));
	}

}
