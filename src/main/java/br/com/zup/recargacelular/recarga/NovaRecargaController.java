package br.com.zup.recargacelular.recarga;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.recargacelular.commons.ValorNaoAceitoException;

@RestController
@RequestMapping("/novaRecarga")
public class NovaRecargaController {
	
	private final String RECARGA_REALIZADA = "Recarga realizada com sucesso.";
	private final String RECARGA_NAO_REALIZADA = "Não foi possível realizar a recarga.";
	
	@Autowired
	private RecargaService recargaService;
	
	@PostMapping
	public ResponseEntity<?> recarregar(@RequestBody @Valid NovaRecargaRequest request) {
		BigDecimal valor = request.getValor();
		Operadora operadora = request.getOperadora();
		
		if (!operadora.validarValor(valor)) {
			throw new ValorNaoAceitoException(valor);
		}
		
		ResponseEntity<?> resposta = recargaService.realizarRecarga(request);
		
		return resposta.getStatusCode().equals(HttpStatus.OK) ? 
				ResponseEntity.ok(new RecargaResponse(request, RECARGA_REALIZADA)) : 
				ResponseEntity.unprocessableEntity().body(new RecargaResponse(request, RECARGA_NAO_REALIZADA));
	}

}
