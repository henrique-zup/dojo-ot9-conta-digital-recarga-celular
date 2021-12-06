package br.com.zup.recargacelular.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;

public class ApiErrorResponse {
	
	private String erro;
	
	private List<Map<String, String>> campos = new ArrayList<Map<String,String>>();

	public ApiErrorResponse(String erro) {
		this.erro = erro;
	}

	public String getErro() {
		return erro;
	}
	
	public List<Map<String, String>> getCampos() {
		return campos;
	}

	public static ApiErrorResponse buildFromMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		ApiErrorResponse response = new ApiErrorResponse("Argumentos inválidos.");
		ex.getBindingResult().getFieldErrors().forEach(fe -> {
			response.adicionaCampo(fe.getField(), fe.getDefaultMessage());
		});
		return response;
	}

	private void adicionaCampo(String field, String message) {
		campos.add(Map.of(field, message));
		
	}
	
}
