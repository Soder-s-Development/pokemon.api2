package com.juliano.pokemon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.juliano.pokemon.api.Model.PoderUnico;
import com.juliano.pokemon.api.Model.PokemonUnico;
import com.juliano.pokemon.config.RespostaPadrao;
import com.juliano.pokemon.response.PokemonUnicoResponse;

import javassist.NotFoundException;

public interface PokemonUnicoService {


	public ResponseEntity<Object> capturar(Long id, String apelido, Long pid, String genero, List<Long> novosPoderes);

	public PoderUnico aprenderPoderPU(PokemonUnico p, Long id);
	
	public Optional<PokemonUnico> getPokemonUnico(Long id);

	public void salvar(PokemonUnico p);
	
	public ResponseEntity<?> deletePokemon(Long id);
	
	public ResponseEntity<?> getPokemonUnicoResponse(Long id);

	public ResponseEntity<RespostaPadrao> getAllMyPokemons(Long personagemId) throws NotFoundException;
	
	public List<PokemonUnicoResponse> getAllMyHoldsPokemons(Long personagemId) throws NotFoundException;
}
