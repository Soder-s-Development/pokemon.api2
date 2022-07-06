package com.juliano.pokemon.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliano.pokemon.api.Model.Personagem;
import com.juliano.pokemon.api.Model.PoderUnico;
import com.juliano.pokemon.api.Model.PokemonExperiencia;
import com.juliano.pokemon.api.Model.PokemonUnico;
import com.juliano.pokemon.repository.PersonagemRepository;
import com.juliano.pokemon.repository.PoderRepository;
import com.juliano.pokemon.repository.PokemonExperienciaRepository;
import com.juliano.pokemon.repository.PokemonRepository;
import com.juliano.pokemon.repository.PokemonUnicoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PokemonUnicoService {
	
	private PokemonRepository pkRepository;
	private PokemonUnicoRepository pkuRepository;
	private PoderRepository pr;
	private PokemonExperienciaRepository pmkER;
	
	@Autowired
	private PersonagemRepository perr;
	
	@Autowired
	private PokemonPoderService pkuService;
	
	public Object capturar(Long id, String apelido, Long pid) {
		if(this.findPersonagem(pid)==false) {
			return null;
		}
		PokemonUnico pkmu = pkuRepository.save(new PokemonUnico(pkRepository.getById(id), apelido, pid));
		pkuService.atualizaPoderes(pkmu.getId(), 1, 1);
		PokemonExperiencia pkmE = new PokemonExperiencia(pkmu.getId());
		int position = this.getPartyLength(pid) + 1;
		System.out.println("positions size is "+position);
		if(position < 7) {
			Personagem p = this.setPokemonParty(pid, position, pkmu.getId());
			System.out.println(p);
			perr.save(p);
		}
		pmkER.save(pkmE);
		return pkmu;
	}
	
	private Boolean findPersonagem(Long id) {
		Optional<Personagem> p = perr.findById(id);
		System.out.println(p.isEmpty());
		if(p.isEmpty()) {
			return false;
		}
		return true;
	}
	
	private Personagem setPokemonParty(Long id, int position, Long pku_id) {
		Optional<Personagem> p = perr.findById(id);
		System.out.println(p.isEmpty());
		if(p.isEmpty()) {
			new Exception("Invalid player");
		}
		if(p.get().getHold_ids().isBlank() || p.get().getHold_ids().isEmpty()) {
			p.get().setHold_ids(String.valueOf(pku_id));
			return p.get();
		}
		String[] result = p.get().getHold_ids().split(",");
		ArrayList<String> list = new ArrayList<>();
		for (String string : result) {
			list.add(string);
		}
		list.add(String.valueOf(pku_id));
		String fs = "";
		for(var i = 0; i < list.size(); i++) {
			if(i==0) {
				fs += list.get(i);
			}else {
				fs += ","+list.get(i);
			}
		}
		p.get().setHold_ids(fs);
		return p.get();
	}
	private int getPartyLength(Long id) {
		Optional<Personagem> p = perr.findById(id);
		System.out.println(p.isEmpty());
		if(p.isEmpty()) {
			new Exception("Invalid player");
		}
		if(!p.get().getHold_ids().isBlank() || !p.get().getHold_ids().isEmpty()) {
			String[] result = p.get().getHold_ids().split(",");
			return result.length;
		}
		return 0;
	}
	public Long aprenderPoderPU(PoderUnico p) {
		PokemonUnico pku  = pkuRepository.findById(p.getId_pokemon_unico()).get();
		if(pku.getPoder1()==null) {
			pku.setPoder1(p.getId());
		}else if(pku.getPoder1()!=null && pku.getPoder2()==null) {
			pku.setPoder2(p.getId());
		}else if(pku.getPoder1()!=null && pku.getPoder2()!=null && pku.getPoder3()==null) {
			pku.setPoder3(p.getId());
		}else if(pku.getPoder1()!=null && pku.getPoder2()!=null && pku.getPoder3()!=null && pku.getPoder4()==null) {
			pku.setPoder4(p.getId());
		}else {
			return 0L;
		}
		pkuRepository.save(pku);
		return p.getId();
	}
	public Long atualizarPoderPU(PoderUnico p, int qual) {
		PokemonUnico pku  = pkuRepository.findById(p.getId_pokemon_unico()).get();
		if(qual==1) {
			pku.setPoder1(p.getId());
		}else if(qual==2) {
			pku.setPoder2(p.getId());
		}else if(qual==3) {
			pku.setPoder3(p.getId());
		}else if(qual==4) {
			pku.setPoder4(p.getId());
		}else {
			return 0L;
		}
		pkuRepository.save(pku);
		return p.getId();
	}
	public PokemonUnico getPokemon(Long id){
		return pkuRepository.findById(id).get();
	}
}