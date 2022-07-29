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

	@Autowired
	private PokemonPoderService poderService;
	
	public WildPokemon genereteWildP(Long id, int level) {
		Pokemon p = pkRepository.findById(id).get();
		WildPokemon wp = new WildPokemon();
		Random rand = new Random(); 
	    int upperbound = (int) 10;
	    int int_random = rand.nextInt(upperbound);
		wp.setNome(p.getNome());
		wp.setNivel(level+int_random);
		if(int_random%2==0) {
			wp.setGenero("M");
		}else {
			wp.setGenero("F");
		}
		wp.setId_pokemon(id);
		wp.setNovo_hp((p.getHp()+int_random+wp.getNivel())*10);
		wp.setNovo_atk(p.getAtk()+int_random+wp.getNivel());
		wp.setNovo_def(p.getDef()+int_random+wp.getNivel());
		wp.setNovo_spa(p.getSpa()+int_random+wp.getNivel());
		wp.setNovo_spd(p.getSpd()+int_random+wp.getNivel());
		wp.setNovo_spe(p.getSpe()+int_random+wp.getNivel());
		wp.setTipo(p.getTipo()+int_random+wp.getNivel());
		wp.setHp_atual(wp.getNovo_hp());
		wp.setPoder1(0L);
		wp.setPoder2(1L);
		return wildpkmr.save(wp);
	}
	public WildPokemon getWild(Long id){
		return wildpkmr.findById(id).get();
	}

	public void salvar(WildPokemon w){
		wildpkmr.save(w);
	}
}
