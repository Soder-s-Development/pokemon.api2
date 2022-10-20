package com.juliano.pokemon.response;

import java.util.Set;

import com.juliano.pokemon.api.Model.PokemonUnico;
import com.juliano.pokemon.api.Model.WildPokemon;

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
public class BatalhaResponse {
	
	private Long id;
	private Long id_conta1;
	private Long id_conta2;
	private Set<PokemonUnicoResponse> pokemonsPlayer1;
	private Set<PokemonUnicoResponse> pokemonsPlayer2;
	private WildPokemon pokemonSelvagem;
	private Long vencedorId;

}
