package br.com.zup.recargacelular.commons.handlers;

import br.com.zup.recargacelular.commons.dto.*;
import br.com.zup.recargacelular.commons.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = { ValorNaoAceitoException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorResponse argumentoInvalidoException(ValorNaoAceitoException ex) {
		return new ApiErrorResponse("Valor de recarga não aceito: %s".replace("%s", ex.getValor().toString()));
	}

	@ExceptionHandler(value = { OperadoraInvalidaException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorResponse argumentoInvalidoException(OperadoraInvalidaException ex) {
		return new ApiErrorResponse("A operadora informada é inválida!");
	}
	
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorResponse argumentoInvalidoException(MethodArgumentNotValidException ex) {
		return ApiErrorResponse.buildFromMethodArgumentNotValidException(ex);
	}

}
