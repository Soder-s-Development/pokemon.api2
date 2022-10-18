package com.juliano.pokemon.api.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.pokemon.api.Model.WildPokemon;
import com.juliano.pokemon.config.RespostaPadrao;
import com.juliano.pokemon.response.BatalhaResponse;
import com.juliano.pokemon.service.BatalhaService;
import com.juliano.pokemon.service.PokemonPoderService;
import com.juliano.pokemon.service.WildPokemonService;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/batalha")
@CrossOrigin(origins = {"*", "x-requested-with", "content-type"}, originPatterns = "*")
@AllArgsConstructor
public class BatalhaController {

	@Autowired
	private BatalhaService service;
	@Autowired
	private WildPokemonService wpms;
	@Autowired
	private PokemonPoderService poderService;

	@PostMapping("/novaBatalha")
	@ResponseStatus(HttpStatus.CREATED)
	public RespostaPadrao iniciarBatalha(
			@RequestParam(name="player1", required=true) Long id1, 
			@RequestParam(name="player2", required=false) Long id2,
			@RequestParam(name="selvagemId", required=false) Long selvagemId) throws Exception {
		BatalhaResponse b = service.iniciarBatalha(id1, id2, selvagemId);
		return RespostaPadrao.builder().status(201).mensagem("Batalha iniciada com o id "+b.getId()).response(b).build();		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RespostaPadrao> getBatalha(@PathVariable Long id) throws NotFoundException {
		return service.getBatalhaResponse(id);
	}

	@GetMapping("/foundPokemon/{level}")
	public ResponseEntity<WildPokemon> foundAPokemon(@PathVariable int level) {
		Random rand = new Random();
		int int_random = rand.nextInt(150);
		return  ResponseEntity.ok(wpms.genereteWildP((long)int_random, level));
	}

	@PostMapping("/attack/{id1}/wild/{id2}/power/{idPU}/{btid}")
	public ResponseEntity Attack(@PathVariable Long id1, @PathVariable Long id2, @PathVariable Long idPU, @PathVariable Long btid) throws NotFoundException{
		return service.ataque(id1, id2, idPU, btid);
	}

	@PostMapping("/wildAttack/{id1}/{id2}/power/{idp}/{btid}")
	public ResponseEntity wildAttack(@PathVariable Long id1,
			@PathVariable Long id2, @PathVariable Long idp, @PathVariable Long btid) throws Exception{
		return service.wildAttack(id1,id2,idp,btid);
	}
}
