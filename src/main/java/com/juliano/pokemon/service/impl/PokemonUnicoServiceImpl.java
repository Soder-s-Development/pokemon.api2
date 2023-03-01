package com.juliano.pokemon.service.impl;

import static com.juliano.pokemon.api.Converter.Converter.from;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.juliano.pokemon.api.Converter.Converter;
import com.juliano.pokemon.api.Model.Personagem;
import com.juliano.pokemon.api.Model.PoderUnico;
import com.juliano.pokemon.api.Model.PokemonExperiencia;
import com.juliano.pokemon.api.Model.PokemonUnico;
import com.juliano.pokemon.config.CustonExceptionHandler;
import com.juliano.pokemon.config.RespostaPadrao;
import com.juliano.pokemon.repository.PersonagemRepository;
import com.juliano.pokemon.repository.PoderRepository;
import com.juliano.pokemon.repository.PoderUnicoRepository;
import com.juliano.pokemon.repository.PokemonExperienciaRepository;
import com.juliano.pokemon.repository.PokemonRepository;
import com.juliano.pokemon.repository.PokemonUnicoRepository;
import com.juliano.pokemon.response.PokemonUnicoResponse;
import com.juliano.pokemon.service.PokemonUnicoService;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class PokemonUnicoServiceImpl implements PokemonUnicoService{
	
	@Autowired
	private PokemonRepository pkRepository;
	
	@Autowired
	private PokemonUnicoRepository pkuRepository;
	
	@Autowired
	private PoderUnicoRepository poderUnicoRepository;
	
	@Autowired
	private PoderRepository poderRepository;
	
	@Autowired
	private PokemonExperienciaRepository experienceRepository;
	
	@Autowired
	private PersonagemRepository personagemRepository;
	
	public ResponseEntity<Object> capturar(Long id, String apelido, Long pid, String genero, List<Long> novosPoderes) {
		Optional<Personagem> p = personagemRepository.findById(pid);
		if(!p.isPresent()) {
			return ResponseEntity.status(404).body("Personagem não encontrado");
		}
		PokemonUnico pkmu = pkuRepository.save(new PokemonUnico(pkRepository.getById(id), apelido, pid, genero, 1, poderUnicoRepository));
		
		novosPoderes.forEach(poder -> {
			new PoderUnico();
			poderUnicoRepository.save(PoderUnico.builder().ativo(true).idPoder(poder).pokemonUnico(pkmu.getId()).level(1).build());
		});
		
		PokemonExperiencia pkmEx = new PokemonExperiencia(pkmu.getId());
		experienceRepository.save(pkmEx);
		p.get().setPokemonIntoParty(pkmu.getId());
		personagemRepository.save(p.get());
		return ResponseEntity.ok(pkmu);
	}

	public PoderUnico aprenderPoderPU(PokemonUnico p, Long id) {
		PoderUnico pu = p.aprenderNovoPoder(id, poderUnicoRepository);
		pkuRepository.save(p);
		return pu;
	}
	
	public Optional<PokemonUnico> getPokemonUnico(Long id){
		return pkuRepository.findById(id);
	}

	public void salvar(PokemonUnico p){
		pkuRepository.save(p);
	}
	
	public ResponseEntity getPokemonUnicoResponse(Long id) {
		Optional<PokemonUnico> optional = pkuRepository.findById(id);
		if(!optional.isPresent()) {
			return CustonExceptionHandler.notFound("Pokemon not found for this id", 404, optional);
		}
		PokemonUnico p = optional.get();
		return ResponseEntity.ok(from(p, poderUnicoRepository, poderRepository));
	}
	
	public ResponseEntity<Boolean> deletePokemon(Long id){
		experienceRepository.deleteById(id);
		pkuRepository.deleteById(id);
		return ResponseEntity.ok(true);
	}

	public List<PokemonUnico> getAllMyPokemons(Long personagemId) throws NotFoundException {
		 return Optional.ofNullable(pkuRepository.findAllByPersonagemId(personagemId))
				 .orElseThrow(() -> new NotFoundException("Nenhum pokemon encontradoo com o id de personagem: "+personagemId));
	}

	public List<PokemonUnicoResponse> getAllMyHoldsPokemons(Long personagemId) throws NotFoundException {
		Personagem p = Optional.ofNullable(personagemRepository.findById(personagemId).get())
				.orElseThrow(() -> new NotFoundException("Nenhum personagem encontradoo com o id: "+personagemId));
		List<PokemonUnicoResponse> pokemons = new ArrayList<>();
		p.getHolds().forEach(id -> {
			try {
				pokemons.add(Converter.from(Optional.ofNullable(pkuRepository.findById(id).get())
						.orElseThrow(() -> new NotFoundException("N'ao foi encontrado o pokemon deste id: "+id)), poderUnicoRepository, poderRepository));
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		});
		return pokemons;
	}
}