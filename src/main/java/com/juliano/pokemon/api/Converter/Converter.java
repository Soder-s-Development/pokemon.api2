package com.juliano.pokemon.api.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.juliano.pokemon.api.Model.Batalha;
import com.juliano.pokemon.api.Model.Bolsa;
import com.juliano.pokemon.api.Model.PoderUnico;
import com.juliano.pokemon.api.Model.PokemonPoder;
import com.juliano.pokemon.api.Model.PokemonUnico;
import com.juliano.pokemon.repository.PoderRepository;
import com.juliano.pokemon.repository.PoderUnicoRepository;
import com.juliano.pokemon.response.BatalhaResponse;
import com.juliano.pokemon.response.BolsaResponse;
import com.juliano.pokemon.response.PoderesResponse;
import com.juliano.pokemon.response.PokemonUnicoResponse;

import javassist.NotFoundException;

public class Converter {
	
	public static PoderesResponse from(PoderUnico pu, PokemonPoder p) {
		PoderesResponse poder = new PoderesResponse();
		poder.setNome(p.getNome());
		poder.setDescricao(p.getDescricao());
		poder.setDanobase(p.getDanobase());
		poder.setEfeito(p.getEfeito());
		poder.setTipo(p.getTipo());
		poder.setLevel(pu.getLevel());
		poder.setSome_effect(pu.getSome_effect());
		poder.setId(pu.getId());
		poder.setAtivo(true);
		return poder;
	}

	public static BolsaResponse from(Bolsa bolsa) {
		BolsaResponse b = new BolsaResponse();
				b.setId(bolsa.getId());
				b.setIdContaAssociada(bolsa.getIdContaAssociada());
				b.setIdPersonagem(bolsa.getIdPersonagem());
				b.setItens(bolsa.getAllItens());
		
		return b;
	}
	
	public static BatalhaResponse from(Batalha b) throws NotFoundException {
		BatalhaResponse response = new BatalhaResponse();
		response.setId(b.getId());
		response.setId_conta1(b.getId_conta1());
		response.setId_conta2(b.getId_conta2());;
		response.setVencedorId(b.getVencedorId());
		response.setPokemonsPlayer1(b.getAllPokemonsInBattlePlayer1());
		if(b.getId_conta2() > 0) {
			response.setPokemonsPlayer2(b.getAllPokemonsInBattlePlayer2());
		}
		if(b.getPokemonSelvagemId() > 0) {
			response.setPokemonSelvagem(b.getPokemonSelvagem());
		}
		return response;
	}
	
	public static PokemonUnicoResponse from(PokemonUnico p, PoderUnicoRepository poderRepository, PoderRepository repository) {
		PokemonUnicoResponse response = new PokemonUnicoResponse();
		List<PoderesResponse> poderList = new ArrayList<>();
		List<PoderUnico> unicoList = p.getPoderesUnicos(poderRepository);
		unicoList.forEach(poderUnico -> 
		{
			try {
				poderList.add(Converter.from(poderUnico, Optional.ofNullable(repository.findById(poderUnico.getIdPoder()).get()).orElseThrow(() -> new NotFoundException("Poder nao encontrado com o id da lista de poderes unicos."))) );
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		});
		response.setPoderes(poderList);
		response.setId(p.getId());
		response.setApelido(p.getApelido());
		response.setNome_pokemon(p.getNome_pokemon());
		response.setConquistas(p.getConquistas());
		response.setCrias(p.getCrias());
		response.setConquistas(p.getConquistas());
		response.setDias_de_vida(p.getDias_de_vida());
		response.setEvoluvao_estado(p.getEvoluvao_estado());
		response.setGenero(p.getGenero());
		response.setNivel(p.getNivel());;
		response.setDias_de_vida(p.getDias_de_vida());
		response.setDias_vivido(p.getDias_vivido());
		response.setEvoluvao_estado(p.getEvoluvao_estado());
		response.setHp_atual(p.getHp_atual());
		response.setNovo_hp(p.getNovo_hp());
		response.setNovo_atk(p.getNovo_atk());
		response.setNovo_def(p.getNovo_def());
		response.setNovo_spa(p.getNovo_spa());
		response.setNovo_spd(p.getNovo_spd());
		response.setNovo_spe(p.getNovo_spe());
		response.setStamina(p.getStamina());
		response.setStamina_atual(p.getStamina_atual());
		response.setTipo(p.getTipo());
		response.setId_pokemon(p.getId_pokemon());
		response.setPersonagem_id(p.getPersonagemId());
		return response;
	}
}
