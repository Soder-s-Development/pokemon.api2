package com.juliano.pokemon.service;

import com.juliano.pokemon.api.Model.*;
import com.juliano.pokemon.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PokemonUnicoService {
	
	@Autowired
	private PokemonRepository pkRepository;
	
	@Autowired
	private PokemonUnicoRepository pkuRepository;
	
	@Autowired
	private PoderUnicoRepository pr;
	
	@Autowired
	private PokemonExperienciaRepository pmkER;
	
	@Autowired
	private PersonagemRepository perr;
	
	@Autowired
	private PokemonPoderService pkuService;
	
	public ResponseEntity<Object> capturar(Long id, String apelido, Long pid, String genero, List<Long> novosPoderes) {
		Optional<Personagem> p = perr.findById(pid);
		if(!p.isPresent()) {
			return ResponseEntity.status(404).body("Personagem inixistente");
		}
		PokemonUnico pkmu = pkuRepository.save(new PokemonUnico(pkRepository.getById(id), apelido, pid, genero, 1, novosPoderes));
		
		pkmu.getPoderes().forEach( poderunico -> {
			pr.save(poderunico);
		});
		
		PokemonExperiencia pkmEx = new PokemonExperiencia(pkmu.getId());
		pmkER.save(pkmEx);
		p.get().setPokemonIntoParty(pkmu.getId());
		perr.save(p.get());
		return ResponseEntity.ok(pkmu);
	}

	public PoderUnico aprenderPoderPU(PokemonUnico p, Long id) {
		PoderUnico pu = p.aprenderNovoPoder(id);
		pr.save(pu);
		pkuRepository.save(p);
		return pu;
	}
	
	public Optional<PokemonUnico> getPokemon(Long id){
		return pkuRepository.findById(id);
	}

	public void salvar(PokemonUnico p){
		pkuRepository.save(p);
	}
}