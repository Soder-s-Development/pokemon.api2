package com.juliano.pokemon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliano.pokemon.api.Model.PokemonUnico;
import com.juliano.pokemon.repository.PoderRepository;
import com.juliano.pokemon.repository.PokemonRepository;
import com.juliano.pokemon.repository.PokemonUnicoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PokemonUnicoService {
	
	private PokemonRepository pkRepository;
	private PokemonUnicoRepository pkuRepository;
	private PoderRepository pr;
	
	@Autowired
	private PokemonPoderService pkuService;
	
	
	public Object capturar(Long id, String apelido) {
		PokemonUnico pkmu = pkuRepository.save(new PokemonUnico(pkRepository.getById(id), apelido));
		pkuService.createFirstMove(pkmu.getId(), pr.getById((long) 1), 0);
		return pkmu;
	}
}