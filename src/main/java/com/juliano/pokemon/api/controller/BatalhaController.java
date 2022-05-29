package com.juliano.pokemon.api.controller;

import java.net.http.HttpResponse;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.pokemon.api.Model.Batalha;
import com.juliano.pokemon.api.Model.WildPokemon;
import com.juliano.pokemon.service.BatalhaService;
import com.juliano.pokemon.service.PokemonService;
import com.juliano.pokemon.service.WildPokemonService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/batalha")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost", "http://127.0.0.1", "http://0.0.0.0", "x-requested-with", "content-type"}, originPatterns = "*")
public class BatalhaController {

	@Autowired
	private BatalhaService btservice;
	@Autowired
	private PokemonService pkms;
	@Autowired
	private WildPokemonService wpms;
	
	@PostMapping("/{id1}/{id2}")
	public Boolean iniciarBatalha(@PathVariable Long id1, @PathVariable Long id2) {
		Batalha b = btservice.iniciarBatalha(id1, id2);
		if(b!=null) {
			return true;
		}
		return false;
	}
	@GetMapping("foundPokemon")
	public ResponseEntity<WildPokemon> foundAPokemon() {
		Random rand = new Random(); 
	      Long upperbound = (long) 150;
	      Long int_random = rand.nextLong(upperbound);
		return  ResponseEntity.ok(wpms.genereteWildP(int_random));
	}
	
}
