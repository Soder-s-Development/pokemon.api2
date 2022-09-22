package com.juliano.pokemon.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.pokemon.api.Model.Pokemon;
import com.juliano.pokemon.api.Model.PokemonUnico;
import com.juliano.pokemon.repository.PokemonUnicoRepository;
import com.juliano.pokemon.service.PokemonService;
import com.juliano.pokemon.service.PokemonUnicoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/pokemons")
@AllArgsConstructor
@CrossOrigin(origins = "*", originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public class Pokemoncontroller {
	
//
//	@Autowired
//	private PokemonUnicoRepository pkmur;
	
	@Autowired
	private PokemonService pservice;
	@Autowired
	private PokemonUnicoService puservice;
	
	@GetMapping
	public List<Pokemon> listar() {
		return pservice.findAll();
	}

	@PostMapping("/cadastrar")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean cadastrarPokemon(@Valid @RequestBody Pokemon pokemon){
		return pservice.salvar(pokemon);
	}
	
	@GetMapping("/{pokemonId}")
	public ResponseEntity<Pokemon> buscar(@PathVariable Long pokemonId) {
		return pservice.retornarPk(pokemonId);
	}
	
	@PostMapping("/capturar/{id}/{apelido}/{personagem_id}/{genero}")
	public ResponseEntity<Object> capturar(@PathVariable String genero, @PathVariable Long id, @PathVariable String apelido, @PathVariable Long personagem_id, @RequestBody List<Long> listPoderes) {
		return puservice.capturar(id, apelido, personagem_id, genero, listPoderes);
	}

	@GetMapping("/estado/{id}")
	public int getEvolucao(@PathVariable Long id){
		Integer i = pservice.getEstado(id);
		return i <= 0 ? 0 : i;
	}
			
//	@GetMapping("/unico/{id}")
//	public PokemonUnico getPoke(@PathVariable Long id) {
//		PokemonUnico pkmu = pkmur.findById(id).get();
//		return pkmu;
//	}
}