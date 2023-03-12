package com.juliano.pokemon.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * 
 * @author soder
 * @apiNote Default response for all endpoints calls
 *
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class RespostaPadrao {
	

	private String mensagem;
	
	private int status;
	
	private Object response;
		
}
