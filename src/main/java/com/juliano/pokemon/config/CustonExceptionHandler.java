package com.juliano.pokemon.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/***
 * 
 * @author soder
 * @apiNote Should be called to answer all exceptions returning an ResponseEntity with a default response inside 
 *
 */
public interface CustonExceptionHandler{

	public static ResponseEntity<RespostaPadrao> notFound(String mensagem, int status, Object response) {
		return new ResponseEntity<RespostaPadrao>(RespostaPadrao.builder().mensagem(mensagem).status(status).response(response).build(), HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<RespostaPadrao> notAllowed(String message, int status, Object response) {
		return new ResponseEntity<RespostaPadrao>(RespostaPadrao.builder().mensagem(message).status(status).response(response).build(), HttpStatus.NOT_ACCEPTABLE);
	}
}
