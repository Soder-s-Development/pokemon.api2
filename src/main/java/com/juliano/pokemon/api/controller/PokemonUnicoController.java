package com.juliano.pokemon.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.pokemon.api.Model.PokemonUnico;
import com.juliano.pokemon.repository.PokemonUnicoRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/poke")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost", "http://127.0.0.1", "http://0.0.0.0", "x-requested-with", "content-type"}, originPatterns = "*")
public class PokemonUnicoController {

	@Autowired
	private PokemonUnicoRepository pkmur;
	
	@GetMapping("/{id}")
	public PokemonUnico getPoke(@PathVariable Long id) {
		PokemonUnico pkmu = pkmur.findById(id).get();
		return pkmu;
	}
}
