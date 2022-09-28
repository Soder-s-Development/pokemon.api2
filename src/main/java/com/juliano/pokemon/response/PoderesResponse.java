package com.juliano.pokemon.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PoderesResponse {
	
	private long id;
	private String nome;
	private String descricao;
	private int danobase;
	private String efeito;
	private String tipo;
	private int level;
	private String some_effect; 
	private boolean ativo;
	
}
