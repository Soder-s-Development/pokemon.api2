package com.juliano.pokemon.api.Model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.juliano.pokemon.repository.PersonagemRepository;
import com.juliano.pokemon.repository.PokemonUnicoRepository;
import com.juliano.pokemon.repository.WildPokemonRepository;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "batalhas")
public class Batalha {
	
	@Transient
	@Autowired
	private PersonagemRepository repository;
	@Transient
	@Autowired
	private PokemonUnicoRepository pokemonRepository;
	@Transient
	@Autowired
	private WildPokemonRepository selvagemRepository;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Long id_conta1;

	private Long id_conta2;
		
	private Long pokemonSelvagemId;
	
	private Long vencedorId;
	
	
	public Set<PokemonUnico> getAllPokemonsInBattlePlayer1() throws NotFoundException{
		Set<PokemonUnico> list = new HashSet<>();
		Personagem p = Optional.ofNullable(repository.findById_conta(id_conta1))
				.orElseThrow(() -> new NotFoundException("Conta player 1 Não encontrada"));
		
		p.getHolds().forEach(id -> {
			try {
				list.add(Optional.ofNullable(pokemonRepository.findById(id).get()).orElseThrow(() -> new NotFoundException("Erro ao buscar pokemon")));
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		});
		return list;
	}
	
	public Set<PokemonUnico> getAllPokemonsInBattlePlayer2() throws NotFoundException{
		Set<PokemonUnico> list = new HashSet<>();
		Personagem p = Optional.ofNullable(repository.findById_conta(id_conta2))
				.orElseThrow(() -> new NotFoundException("Conta player 2 Não encontrada"));
		
		p.getHolds().forEach(id -> {
			try {
				list.add(Optional.ofNullable(pokemonRepository.findById(id).get()).orElseThrow(() -> new NotFoundException("Erro ao buscar pokemon")));
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		});
		return list;
	}
	
	public WildPokemon getPokemonSelvagem() throws NotFoundException {
		return Optional.ofNullable(selvagemRepository.findById(this.pokemonSelvagemId).get())
		.orElseThrow(() -> new NotFoundException("Pokemon selvagem não encontrado ou inixistente"));
	}
}
