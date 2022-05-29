package com.juliano.pokemon.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliano.pokemon.api.Model.Pokemon;
import com.juliano.pokemon.api.Model.WildPokemon;
import com.juliano.pokemon.repository.PersonagemRepository;
import com.juliano.pokemon.repository.PoderRepository;
import com.juliano.pokemon.repository.PokemonExperienciaRepository;
import com.juliano.pokemon.repository.PokemonRepository;
import com.juliano.pokemon.repository.PokemonUnicoRepository;
import com.juliano.pokemon.repository.WildPokemonRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class WildPokemonService {

	@Autowired
	private WildPokemonRepository wildpkmr;
	@Autowired
	private PokemonRepository pkRepository;
	
	public WildPokemon genereteWildP(Long id) {
		Pokemon p = pkRepository.findById(id).get();
		WildPokemon wp = new WildPokemon();
		Random rand = new Random(); 
	    int upperbound = (int) 10;
	    int int_random = rand.nextInt(upperbound);
		wp.setNome(p.getNome());
		if(int_random%2==0) {
			wp.setGenero("masculino");
		}else {
			wp.setGenero("femenino");
		}
		wp.setId_pokemon(id);
		wp.setNivel(1);
		wp.setNovo_hp(p.getHp()+int_random);
		wp.setNovo_atk(p.getAtk());
		wp.setNovo_def(p.getDef());
		wp.setNovo_spa(p.getSpa());
		wp.setNovo_spd(p.getSpd());
		wp.setNovo_spe(p.getSpe());
		wp.setTipo(p.getTipo());
		wp.setPoder1(0L);
		wp.setPoder2(1L);
		return wildpkmr.save(wp);
	}
	
}
