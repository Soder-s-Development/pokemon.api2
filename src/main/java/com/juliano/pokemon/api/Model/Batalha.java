package com.juliano.pokemon.api.Model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.juliano.pokemon.api.Converter.Converter;
import com.juliano.pokemon.repository.PersonagemRepository;
import com.juliano.pokemon.repository.PoderRepository;
import com.juliano.pokemon.repository.PoderUnicoRepository;
import com.juliano.pokemon.repository.PokemonUnicoRepository;
import com.juliano.pokemon.repository.WildPokemonRepository;
import com.juliano.pokemon.response.PokemonUnicoResponse;

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
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Long id_conta1;

	private Long id_conta2;
		
	private Long pokemonSelvagemId;
	
	@Column(name = "vencedorId")
	private Long vencedorId;
	
	
	public Set<PokemonUnicoResponse> getAllPokemonsInBattlePlayer1(PersonagemRepository repository, PokemonUnicoRepository pokemonRepository, PoderUnicoRepository poderUnicoRepository, PoderRepository poderRepository) throws NotFoundException{
		Set<PokemonUnicoResponse> list = new HashSet<>();
		Personagem p = Optional.ofNullable(repository.findById_conta(id_conta1))
				.orElseThrow(() -> new NotFoundException("Conta player 1 Não encontrada"));
		
		p.getHolds().forEach(id -> {
			try {
				PokemonUnico pkm = Optional.ofNullable(pokemonRepository.findById(id).get()).orElseThrow(() -> new NotFoundException("Erro ao buscar pokemon"));
				list.add(Converter.from(pkm, poderUnicoRepository, poderRepository));
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		});
		return list;
	}
	
	public Set<PokemonUnicoResponse> getAllPokemonsInBattlePlayer2(PersonagemRepository repository, PokemonUnicoRepository pokemonRepository, PoderUnicoRepository poderUnicoRepository, PoderRepository poderRepository) throws NotFoundException{
		Set<PokemonUnicoResponse> list = new HashSet<>();
		Personagem p = Optional.ofNullable(repository.findById_conta(id_conta2))
				.orElseThrow(() -> new NotFoundException("Conta player 2 Não encontrada"));
		
		p.getHolds().forEach(id -> {
			try {
				PokemonUnico pkm = Optional.ofNullable(pokemonRepository.findById(id).get()).orElseThrow(() -> new NotFoundException("Erro ao buscar pokemon"));
				list.add(Converter.from(pkm, poderUnicoRepository, poderRepository));
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		});
		return list;
	}
	
	public WildPokemon getPokemonSelvagem(WildPokemonRepository selvagemRepository) throws NotFoundException {
		return Optional.ofNullable(selvagemRepository.findById(this.pokemonSelvagemId).get())
		.orElseThrow(() -> new NotFoundException("Pokemon selvagem não encontrado ou inixistente"));
	}
	
	public Boolean playr1IsPresent() {
		return id_conta1 != null && id_conta1 > 0;
	}
}
