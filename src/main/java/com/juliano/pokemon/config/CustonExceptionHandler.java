package com.juliano.pokemon.config;

import org.springframework.http.ResponseEntity;

public interface CustonExceptionHandler{

	public static ResponseEntity<RespostaPadrao> notFound(String mensagem, int status, Object response) {
		return new ResponseEntity<RespostaPadrao>(RespostaPadrao.builder().mensagem(mensagem).status(status).response(response).build(), null, 404);
	}
}
