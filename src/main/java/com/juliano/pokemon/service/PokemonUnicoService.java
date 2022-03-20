package com.juliano.pokemon.service;

import org.springframework.stereotype.Service;

import com.juliano.pokemon.api.Model.PokemonUnico;
import com.juliano.pokemon.repository.PokemonRepository;
import com.juliano.pokemon.repository.PokemonUnicoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PokemonUnicoService {
	
	private PokemonRepository pkRepository;
	private PokemonUnicoRepository pkuRepository;
	
	public Object capturar(Long id, String apelido) {
		return pkuRepository.save(new PokemonUnico(pkRepository.getById(id), apelido));
	}
}