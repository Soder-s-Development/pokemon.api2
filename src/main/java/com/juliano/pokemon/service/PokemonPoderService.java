package com.juliano.pokemon.service;

import com.juliano.pokemon.repository.PoderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliano.pokemon.api.Model.Pokemon;
import com.juliano.pokemon.api.Model.PokemonPoder;
import com.juliano.pokemon.api.Model.PokemonUnico;
import com.juliano.pokemon.repository.PokemonUnicoRepository;

import lombok.AllArgsConstructor;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PokemonPoderService {
	@Autowired
	private PokemonUnicoRepository pkmur;
	@Autowired
	private PoderRepository pdrr;

	public PokemonUnico aprendePoder(long pkuid, long pdu){
		Optional<PokemonUnico> pkmu = pkmur.findById(pkuid);
		if(pkmu.isEmpty() == true)
			new Exception("Pokemon não encontrado");
		int posicao = this.posicaoDisponivel(pkmu.get());
		if(posicao < 0){
			new Exception("Todas as posições estão preenchidas");
		}
		switch (posicao){
			case 1:
				pkmu.get().setPoder1(pdu);
				break;
			case 2:
				pkmu.get().setPoder2(pdu);
				break;
			case 3:
				pkmu.get().setPoder3(pdu);
				break;
			case 4:
				pkmu.get().setPoder4(pdu);
				break;
		}
		return pkmur.save(pkmu.get());
	}
	public int posicaoDisponivel(PokemonUnico pk){
		boolean[] arr = {false, false, false, false};
		if(pk.getPoder1() > 0){
			arr[0] = true;
		}  if (pk.getPoder2() > 0) {
			arr[1] = true;
		}  if (pk.getPoder3() > 0) {
			arr[2] = true;
		} if (pk.getPoder4() > 0) {
			arr[3] = true;
		}
		for(var i = 0; i < arr.length; i++){
			if (arr[i] == false){
				return i+1;
			}
		}
		return -1;
	}
	public PokemonUnico atualizaPoderes(long pkmuid, long idpoder, int position) {
		PokemonUnico pkmu = pkmur.findById(pkmuid).get();
		switch (position) {
		case 2:
			pkmu.setPoder2(idpoder);
			break;
		case 3:
			pkmu.setPoder3(idpoder);
			break;
		case 4:
			pkmu.setPoder4(idpoder);
			break;
		default:
			pkmu.setPoder1(idpoder);
			break;
		}
		
		return pkmur.save(pkmu);
	}

	public PokemonPoder getPoder(Long id){
		return pdrr.findById(id).orElse(null);
	}
	
	public int calculaDano(Pokemon pkm_atk, Pokemon pkm_def, PokemonPoder pp) {
		int aatk = (pp.getDanobase()+pkm_atk.getAtk()+pkm_atk.getSpe())-(pkm_def.getDef()+pkm_def.getSpe());
		int aspa = (pp.getDanobase()+pkm_atk.getSpa()+pkm_atk.getSpe())-(pkm_def.getSpd()+pkm_def.getSpe());
		
		if(aatk < 0)
			aatk = 1;
		if(aspa < 0)
			aspa = 1;
		
		switch(pp.getEfeito()) {
		 	case "grass":
		 		if(pkm_def.getTipo().contains("water") || pkm_def.getTipo().contains("ground") || pkm_def.getTipo().contains("stone")) {
		 			aatk = (int) (aatk*0.5);
		 			aspa = (int) (aspa*0.5);
		 			break;
		 		}
		 	case "water":
		 		if(pkm_def.getTipo().contains("stone") || pkm_def.getTipo().contains("fire") || pkm_def.getTipo().contains("ground")) {
		 			aatk = (int) (aatk*0.5);
		 			aspa = (int) (aspa*0.5);
		 			break;
		 		}
		 	case "dragon":
		 		if(pkm_def.getTipo().contains("dragon")) {
		 			aatk = (int) (aatk*0.8);	
		 			aspa = (int) (aspa*0.8);
		 			break;
		 		}
		 	case "eletric":
		 		if(pkm_def.getTipo().contains("flying") || pkm_def.getTipo().contains("water")) {
		 			aatk = (int) (aatk*0.5);	
		 			aspa = (int) (aspa*0.5);
		 			break;
		 		}
		 	case "ghost":
		 		if(pkm_def.getTipo().contains("normal") || pkm_def.getTipo().contains("psychic") || pkm_def.getTipo().contains("Fighting")) {
		 			aatk = (int) (aatk*0.5);	
		 			aspa = (int) (aspa*0.5);
		 			break;
		 		}else if(pkm_def.getTipo().contains("ghost")) {
		 			aatk = (int) (aatk*0.3);	
		 			aspa = (int) (aspa*0.3);
		 			break;
		 		}
		 	case "fairy":
		 		if(pkm_def.getTipo().contains("Fighting") || pkm_def.getTipo().contains("dragon") || pkm_def.getTipo().contains("dark")) {
		 			aatk = (int) (aatk*0.5);	
		 			aspa = (int) (aspa*0.5);
		 			break;
		 		}
		 	case "fire":
		 		if(pkm_def.getTipo().contains("grass") || pkm_def.getTipo().contains("steel") || pkm_def.getTipo().contains("bug") || pkm_def.getTipo().contains("ice")) {
		 			aatk = (int) (aatk*0.5);	
		 			aspa = (int) (aspa*0.5);
		 			break;
		 		}
		 	case "ice":
		 		if(pkm_def.getTipo().contains("grass") || pkm_def.getTipo().contains("ground") || pkm_def.getTipo().contains("dragon") || pkm_def.getTipo().contains("flying") || pkm_def.getTipo().contains("bug")) {
		 			aatk = (int) (aatk*0.5);	
		 			aspa = (int) (aspa*0.5);
		 			break;
		 		}
		 	case "bug":
		 		if(pkm_def.getTipo().contains("grass") || pkm_def.getTipo().contains("dark") || pkm_def.getTipo().contains("psychic")) {
		 			aatk = (int) (aatk*0.5);	
		 			aspa = (int) (aspa*0.5);
		 			break;
		 		}
		 	case "fighting":
		 		if(pkm_def.getTipo().contains("ice") || pkm_def.getTipo().contains("ground") || pkm_def.getTipo().contains("stone") || pkm_def.getTipo().contains("dark") || pkm_def.getTipo().contains("normal") || pkm_def.getTipo().contains("steel")) {
		 			aatk = (int) (aatk*0.5);	
		 			aspa = (int) (aspa*0.5);
		 			break;
		 		}
		 	case "steel":
		 		if(pkm_def.getTipo().contains("ice") || pkm_def.getTipo().contains("ground") || pkm_def.getTipo().contains("fairy") || pkm_def.getTipo().contains("normal")) {
		 			aatk = (int) (aatk*0.5);	
		 			aspa = (int) (aspa*0.5);
		 			break;
		 		}
		 	case "dark":
		 		if(pkm_def.getTipo().contains("ghost") || pkm_def.getTipo().contains("psychic")) {
		 			aatk = (int) (aatk*0.5);	
		 			aspa = (int) (aspa*0.5);
		 			break;
		 		}
		 	case "psychic":
		 		if(pkm_def.getTipo().contains("fighting") || pkm_def.getTipo().contains("poison")) {
		 			aatk = (int) (aatk*0.5);	
		 			aspa = (int) (aspa*0.5);
		 			break;
		 		}
		 	case "stone":
		 		if(pkm_def.getTipo().contains("flying") || pkm_def.getTipo().contains("eletric") || pkm_def.getTipo().contains("poison") || pkm_def.getTipo().contains("bug") || pkm_def.getTipo().contains("fire") || pkm_def.getTipo().contains("normal")) {
		 			aatk = (int) (aatk*0.5);	
		 			aspa = (int) (aspa*0.5);
		 			break;
		 		}
		 	case "ground":
		 		if(pkm_def.getTipo().contains("eletric") || pkm_def.getTipo().contains("steel") || pkm_def.getTipo().contains("fire") || pkm_def.getTipo().contains("poison")) {
		 			aatk = (int) (aatk*0.5);	
		 			aspa = (int) (aspa*0.5);
		 			break;
		 		}else if(pkm_def.getTipo().contains("stone")) {
		 			aatk = (int) (aatk*0.3);
		 			aspa = (int) (aspa*0.3);
		 			break;
		 		}
		 	case "poison":
		 		if(pkm_def.getTipo().contains("grass") || pkm_def.getTipo().contains("fighting") || pkm_def.getTipo().contains("fairy")) {
		 			aatk = (int) (aatk*0.5);	
		 			aspa = (int) (aspa*0.5);
		 			break;
		 		}
		 	case "flying":
		 		if(pkm_def.getTipo().contains("grass") || pkm_def.getTipo().contains("bug") || pkm_def.getTipo().contains("fighting") || pkm_def.getTipo().contains("normal")) {
		 			aatk = (int) (aatk*0.5);	
		 			aspa = (int) (aspa*0.5);
		 			break;
		 		}
		 }
		if (pp.getTipo().contains("physical")) {
			return aatk;
		}
		return aspa;
	}
}
