package com.juliano.pokemon.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.pokemon.api.Model.PokemonPoder;
import com.juliano.pokemon.repository.PoderRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/poderes")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost", "http://127.0.0.1", "http://0.0.0.0", "x-requested-with", "content-type"}, originPatterns = "*")
public class PoderController {

	private PoderRepository prepo;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Object cadastrarPoder(@RequestBody PokemonPoder p) {
		return prepo.save(p);
	}
	
	@GetMapping
	public java.util.List<PokemonPoder> getPoderes(){
		return prepo.findAll();
	}
}
