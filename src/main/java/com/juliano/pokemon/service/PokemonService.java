package com.juliano.pokemon.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.juliano.pokemon.api.Model.Pokemon;
import com.juliano.pokemon.repository.PokemonRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PokemonService {
	
	private PokemonRepository pokemonRepository;
	
	
	public List<Pokemon> findAll() {
		return pokemonRepository.findAll();
	}
	public boolean salvar(Pokemon pk) {
		return pokemonRepository.save(pk) != null;
	}
	
	public ResponseEntity<Pokemon> retornarPk(Long id){
		return pokemonRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	public Pokemon getPokemon(Long id){
		return 	pokemonRepository.findById(id).get();
	}
}
