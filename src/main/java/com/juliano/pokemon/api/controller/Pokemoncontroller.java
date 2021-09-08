package com.juliano.pokemon.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.pokemon.api.Model.Pokemon;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/pokemons")
@AllArgsConstructor
public class Pokemoncontroller {
	
	private PokemonRepository pokemonRepository;
	

	public Pokemoncontroller(PokemonRepository pokemonRepository) {
		super();
		this.pokemonRepository = pokemonRepository;
	}

	@GetMapping
	public List<Pokemon> listar() {
		return pokemonRepository.findAll();
	}

	@PostMapping("/cadastrar")
	@ResponseStatus(HttpStatus.CREATED)
	public Pokemon cadastrarPokemon(@Valid @RequestBody Pokemon pokemon){
		return pokemonRepository.save(pokemon);
	}
	
	@GetMapping("/{pokemonId}")
	public ResponseEntity<Pokemon> buscar(@PathVariable Long pokemonId) {
	
		return pokemonRepository.findById(pokemonId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		
			/* codigo não otimizado x codigo otimizado, ambos funcionam
			 * Optional<Cliente> cliente = clienteRepository.findById(clinentId);
		
			if(cliente.isPresent()) {
				return ResponseEntity.ok(cliente.get());
			}
			
			return ResponseEntity.notFound().build();*/
	}
			
	
}