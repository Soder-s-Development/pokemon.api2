package com.juliano.pokemon.api.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.pokemon.api.Model.Bolsa;
import com.juliano.pokemon.repository.BolsaRepository;
import com.juliano.pokemon.service.BolsaService;
import com.juliano.pokemon.service.PokemonService;
import com.juliano.pokemon.service.PokemonUnicoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/bolsa")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost", "http://127.0.0.1", "http://0.0.0.0", "x-requested-with", "content-type"}, originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public class BolsaController {
	
	private BolsaService bls;
	
	@PostMapping
	public Bolsa criarBolsa(@Valid @RequestBody Bolsa bolsa) {
		return bls.criarBolsa(bolsa);
	}
	@PatchMapping("/item/{id}/{item}")
	public Bolsa salvarItem(@PathVariable Long id, @PathVariable String item) {
		return bls.atualizarItem(id, item);		
	}
	@GetMapping("{id}")
	public ResponseEntity<Bolsa> getBolsa(@PathVariable Long id) {
		return bls.getBolsa(id);
	}
}
