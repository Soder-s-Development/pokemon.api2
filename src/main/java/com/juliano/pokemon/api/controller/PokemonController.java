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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.pokemon.api.Model.Pokemon;
import com.juliano.pokemon.config.RespostaPadrao;
import com.juliano.pokemon.service.PokemonService;
import com.juliano.pokemon.service.impl.PokemonUnicoServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/pokemons")
@AllArgsConstructor
@CrossOrigin(origins = "*", originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public class PokemonController {
	
//
//	@Autowired
//	private PokemonUnicoRepository pkmur;
	
	@Autowired
	private PokemonService pservice;
	@Autowired
	private PokemonUnicoServiceImpl puservice;
	
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
	
	@PostMapping("/capturar/{id}/{personagem_id}")
	public ResponseEntity<Object> capturar(@RequestParam(name = "genero", required = false) String genero, 
			@PathVariable Long id, 
			@RequestParam(name="apelido", required=false) String apelido, 
			@PathVariable Long personagem_id, @RequestBody List<Long> listPoderes) {
		return puservice.capturar(id, apelido, personagem_id, genero, listPoderes);
	}

	@GetMapping("/estado/{id}")
	public ResponseEntity<RespostaPadrao> getEvolucao(@PathVariable Long id){
		Integer i = pservice.getEstado(id);
		if(i == -1) {
			return ResponseEntity.badRequest().body(RespostaPadrao.builder().status(404).mensagem("Not exist pokemon for this id yet").build());
		}
		return i <= 0 ? ResponseEntity.ok(RespostaPadrao.builder().status(200).response(0).build()) : ResponseEntity.ok(RespostaPadrao.builder().status(200).response(i).build());
	}
			
//	@GetMapping("/unico/{id}")
//	public PokemonUnico getPoke(@PathVariable Long id) {
//		PokemonUnico pkmu = pkmur.findById(id).get();
//		return pkmu;
//	}
}