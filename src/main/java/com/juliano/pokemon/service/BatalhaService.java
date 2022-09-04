package com.juliano.pokemon.service;

import com.juliano.pokemon.api.Model.Batalha;
import com.juliano.pokemon.api.Model.Personagem;
import com.juliano.pokemon.api.Model.PokemonUnico;
import com.juliano.pokemon.api.Model.WildPokemon;
import com.juliano.pokemon.repository.BatalhaRepository;
import com.juliano.pokemon.repository.PersonagemRepository;
import com.juliano.pokemon.repository.PokemonUnicoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BatalhaService {

	@Autowired
	private BatalhaRepository br;
	@Autowired
	private PersonagemRepository pr;
	@Autowired
	private PokemonUnicoRepository pur;

	
	// em construção
	public ResponseEntity iniciarBatalha(long idconta1, long idconta2) {
		if(idconta1 < 1 && idconta2 < 1) return ResponseEntity.status(400).body("Nenhum id válido foi informado");
		Personagem p = pr.findById_conta(idconta1);
		List<String> list = new ArrayList();
		if(p.getHold_ids().split(",").length >= 1) list = List.of(p.getHold_ids().split(","));
		else {
			list.add(p.getHold_ids());
		}

		List<PokemonUnico> listpkm = new ArrayList<>();
		for (String id: list) {
			Optional<PokemonUnico> pkmu = pur.findById(Long.valueOf(id));
			if ( pkmu.isPresent() ){
				listpkm.add(pkmu.get());
			}
		}
		int hpTotal = 0;
		for(PokemonUnico pkmul :listpkm) {
			hpTotal += pkmul.getHp_atual() > 0 ? pkmul.getHp_atual() : 0;
		}
		if(hpTotal == 0){
			return ResponseEntity.status(404).body("Nenhum pokemon vivo encontrado na sua lista");
		}
		Batalha bt = new Batalha();
		bt.setId_conta1(idconta1);
		bt.setId_conta2(idconta2);
		return ResponseEntity.ok(br.save(bt));
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

	public Optional<Batalha> getBatalha(Long id){
		return br.findById(id);
	}
	public void save(Batalha bt){
		br.save(bt);
	}
}
