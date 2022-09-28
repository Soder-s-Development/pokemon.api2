package com.juliano.pokemon.response;

import java.util.List;

import com.juliano.pokemon.api.Model.Item;

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
public class BolsaResponse {
   
	private Long id;
	private Long idPersonagem;
	private Long idContaAssociada;
	private List<Item> itens;
	
}
