package com.juliano.pokemon.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.pokemon.api.Converter.Converter;
import com.juliano.pokemon.api.Model.PokemonUnico;
import com.juliano.pokemon.repository.PokemonExperienciaRepository;
import com.juliano.pokemon.repository.PokemonUnicoRepository;
import com.juliano.pokemon.response.PokemonUnicoResponse;
import com.juliano.pokemon.service.PokemonUnicoService;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/unico")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost", "http://127.0.0.1", "http://0.0.0.0", "x-requested-with", "content-type"}, originPatterns = "*")
public class PokemonUnicoController {

	@Autowired
	private PokemonUnicoRepository pkmur;
	
	@Autowired
	private PokemonExperienciaRepository pmer;
	
	private PokemonUnicoService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<PokemonUnicoResponse> getPoke(@PathVariable Long id) throws NotFoundException {
		return service.getPokemonUnicoResponse(id);
	}
	
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable Long id) {
		pmer.deleteById(id);
		pkmur.deleteById(id);
		return true;
	}
	
	@GetMapping("/all/{personagemId}")
	public ResponseEntity<List<PokemonUnico>> getAllMyPokemons(@PathVariable Long personagemId) throws NotFoundException{
		return ResponseEntity.ok(service.getAllMyPokemons(personagemId));
	}
	
	@GetMapping("/holds/{personagemId}")
	public ResponseEntity<List<PokemonUnicoResponse>> getAllMyHoldsPokemons(@PathVariable Long personagemId) throws NotFoundException{
		return ResponseEntity.ok(service.getAllMyHoldsPokemons(personagemId));
	}
	
}
