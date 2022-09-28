package com.juliano.pokemon.api.controller;

import com.juliano.pokemon.api.Model.*;
import com.juliano.pokemon.repository.BatalhaRepository;
import com.juliano.pokemon.repository.PersonagemRepository;
import com.juliano.pokemon.repository.PoderUnicoRepository;
import com.juliano.pokemon.repository.PokemonUnicoRepository;
import com.juliano.pokemon.repository.WildPokemonRepository;
import com.juliano.pokemon.service.*;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/batalha")
@CrossOrigin(origins = {"http://localhost", "http://127.0.0.1", "http://0.0.0.0", "x-requested-with", "content-type"}, originPatterns = "*")
@AllArgsConstructor
public class BatalhaController {

	@Autowired
	private BatalhaService service;
	@Autowired
	private WildPokemonService wpms;
	@Autowired
	private PokemonPoderService poderService;

	@PostMapping("/{id1}")
	public ResponseEntity<Any> iniciarBatalha(@PathVariable Long id1, 
			@RequestParam(name="player2", required=false) Long id2, @RequestParam(name="selvagemId", required=false) Long selvagemId) throws Exception {
		return service.iniciarBatalha(id1, id2, selvagemId);
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
