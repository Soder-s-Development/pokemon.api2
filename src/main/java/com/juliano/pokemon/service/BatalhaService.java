package com.juliano.pokemon.service;

import com.juliano.pokemon.api.Model.PokemonUnico;
import com.juliano.pokemon.api.Model.WildPokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliano.pokemon.api.Model.Batalha;
import com.juliano.pokemon.repository.BatalhaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BatalhaService {

	@Autowired
	private BatalhaRepository br;
	
	// em construção
	public Batalha iniciarBatalha(long idconta1, long idconta2) {
		Batalha bt = new Batalha();
		bt.setId_conta1(idconta1);
		bt.setId_conta2(idconta2);
		return br.save(bt);
	}

	public boolean teste(){
		return true;
	}

	//batalha
	public Batalha batalharContraPokeSelvagem(Long idConta, PokemonUnico p, WildPokemon w){
		Batalha bt = new Batalha();
		bt.setId_conta1(idConta);
		bt.setId_pokemon1(p.getId());
		bt.setVida_p1(p.getNovo_hp());
		bt.setId_conta2(0L);
		bt.setId_pokemon7(w.getId());
		bt.setVida_p7(w.getNovo_hp());
		return  br.save(bt);
	}

	public Batalha getBatalha(Long id){
		return br.findById(id).get();
	}
	public void save(Batalha bt){
		br.save(bt);
	}
}
