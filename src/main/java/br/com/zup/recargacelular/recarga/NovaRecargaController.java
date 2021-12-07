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
	
	private final String RECARGA_REALIZADA = "Recarga realizada com sucesso.";
	private final String RECARGA_NAO_REALIZADA = "Não foi possível realizar a recarga.";
	
	@Autowired
	private RecargaService recargaService;
	
	@PostMapping
	public ResponseEntity<?> recarregar(@RequestBody @Valid NovaRecargaRequest request) {
		var recarga = new Recarga(request);

		ResponseEntity<?> resposta = recargaService.realizarRecarga(recarga);
		
		return resposta.getStatusCode().equals(HttpStatus.OK)
				? ResponseEntity.ok(recarga.toResponse(RECARGA_REALIZADA))
				: ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(recarga.toResponse(RECARGA_NAO_REALIZADA));
	}

}
