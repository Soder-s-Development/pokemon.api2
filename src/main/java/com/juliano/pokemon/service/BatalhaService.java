package com.juliano.pokemon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.juliano.pokemon.api.Model.Batalha;
import com.juliano.pokemon.api.Model.Personagem;
import com.juliano.pokemon.api.Model.PoderUnico;
import com.juliano.pokemon.api.Model.PokemonPoder;
import com.juliano.pokemon.api.Model.PokemonUnico;
import com.juliano.pokemon.api.Model.WildPokemon;
import com.juliano.pokemon.repository.BatalhaRepository;
import com.juliano.pokemon.repository.PersonagemRepository;
import com.juliano.pokemon.repository.PoderUnicoRepository;
import com.juliano.pokemon.repository.PokemonUnicoRepository;
import com.juliano.pokemon.repository.WildPokemonRepository;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BatalhaService {

	@Autowired
	private BatalhaRepository BatalhaRepository;
	
	@Autowired
	private PersonagemRepository personagemRepository;
	
	@Autowired
	private PokemonUnicoRepository pokemonUnicoRepository;
	
	@Autowired
	private WildPokemonRepository selvagemRepository;
	
	@Autowired
	private PokemonUnicoService pmus;
	
	@Autowired
	private WildPokemonService wpms;
	
	@Autowired
	private PokemonPoderService poderService;
	
	@Autowired
	private PokemonService pkms;
	
	@Autowired
	private PoderUnicoRepository pdrur;

	// em construção
	public ResponseEntity iniciarBatalha(Long idconta1, Long idconta2, Long selvagemId) throws Exception {
		Batalha bt = new Batalha();
		
		if (idconta1 < 1 && !(idconta2 > 0)) {
			return ResponseEntity.status(400).body("Nenhum id válido foi informado");
		}
		else if(idconta1 > 0) {
			bt = setPlayerIntoBattle(bt, idconta1);
		}
		
		if(idconta2!=null && idconta2 > 0 && !(selvagemId > 0)) {
			bt = setPlayerIntoBattle(bt, idconta2);
		}
		else {
			bt = setWildPokemonIntoBattle(bt, selvagemId);
		}
		
		return ResponseEntity.ok(BatalhaRepository.save(bt));
	}

	private Batalha setWildPokemonIntoBattle(Batalha bt, Long selvagemId) throws NotFoundException {
		bt.setPokemonSelvagemId(selvagemId);
		return bt;
	}

	public boolean teste() {
		return true;
	}

	@SuppressWarnings("unused")
	private Batalha setPlayerIntoBattle(Batalha bt, Long idconta) throws Exception {
		Personagem p = personagemRepository.findById_conta(idconta);
		
		Set<Long> listpkmIds = p.getHolds();
		
		List<PokemonUnico> pokemons = new ArrayList<PokemonUnico>();
		
		for (Long id: listpkmIds) {
			PokemonUnico pkmu = Optional.ofNullable(pokemonUnicoRepository.findById(id).get()).orElseThrow(() -> new NotFoundException("Um ou mais pokemons não foram encontrados"));
			pokemons.add(pkmu);
		}
		
		int hpTotal = 0;
		for(PokemonUnico pkmul :pokemons) {
			hpTotal += pkmul.getHp_atual() > 0 ? pkmul.getHp_atual() : 0;
		}
		if(hpTotal == 0){
			new IllegalStateException("Nenhum pokemon vivo");
		}
		
		if(bt.getId_conta1() > 0L) {
			bt.setId_conta2(idconta);
		}else {
			bt.setId_conta1(idconta);
		}
		
		return bt;
	}
	
	public Batalha getBatalha(Long id) throws NotFoundException {
		return Optional.ofNullable(BatalhaRepository.findById(id).get()).orElseThrow(() -> new NotFoundException("Batalha não encontrada"));
	}
	
	public void salvar(Batalha b) {
		BatalhaRepository.save(b);
	}
	
	public ResponseEntity wildAttack(Long id1, Long id2, Long idp, Long btid) throws Exception {
		if ( idp < 1 ) idp = Long.valueOf(1);
		if(id1 < 1 || id2 < 1 || id2 < 1 || btid < 1){
			ResponseEntity.status(400)
					.body("Parâmetros inválidos na requisição");
		}
		Optional<PokemonUnico> pu = pmus.getPokemonUnico(id2);
		if ( !pu.isPresent() ){
			ResponseEntity.notFound().build();
		}
		PokemonUnico p = pu.get();
		WildPokemon w = wpms.getWild(id1);
		PokemonPoder pp = poderService.getPoder(idp);
		Batalha bt = getBatalha(btid);

		if(w == null){
			return ResponseEntity.status(404).body("Pokemon Selvagem não existe");
		}
		if(pp == null){
			return ResponseEntity.status(404).body("Poder não existe");
		}
		int dano = poderService.calculaDano(pkms.getPokemon(w.getId_pokemon()), pkms.getPokemon(p.getId_pokemon()), pp);
		if((p.getHp_atual()-dano)<1){
			p.setHp_atual(0);
			pmus.salvar(p);
			bt.setVencedorId(0L);
			salvar(bt);
			var res = "dano:"+dano+",hp_pokemon: 0";
			return ResponseEntity.ok(res);
		}
		
		p.setHp_atual(p.getHp_atual()-dano);
		String res = "dano:"+dano+",hp_pokemon: "+p.getHp_atual();
		pmus.salvar(p);
		salvar(bt);
		pmus.salvar(p);
		return ResponseEntity.ok(res);
	}

	public ResponseEntity ataque(Long id1, Long id2, Long idPU, Long btid) throws NotFoundException {
		if(id1 < 1 || id2 < 1 || id2 < 1 || idPU < 1 || btid < 1){
			ResponseEntity.status(400)
					.body("Parâmetros inválidos na requisição");
		}
		var p = pmus.getPokemonUnico(id1);
		p = p != null ? p : null;
		WildPokemon w = wpms.getWild(id2);
		PoderUnico pu = pdrur.findById(idPU).get();
		PokemonPoder pp = poderService.getPoder(pu.getIdPoder());
		
		Batalha bt = getBatalha(btid);
		
		if (!p.isPresent() || !p.get().podeAtacar(pp)){
			p.get().setStamina_atual(p.get().getStamina_atual()+1);
			pmus.salvar(p.get());
			return ResponseEntity.ok("Can't attack");
		}
		int dano = poderService.calculaDano(pkms.getPokemon(p.get().getId_pokemon()), pkms.getPokemon(w.getId_pokemon()), pp);
		dano = dano+(pu.getLevel()*30);

		if((w.getHp_atual()-dano)<1){
			w.setHp_atual(0);
			bt.getPokemonSelvagem().setHp_atual(0);
			wpms.salvar(w);
			bt.setVencedorId(p.get().getPersonagemId());
			salvar(bt);
			var json = "dano:"+dano+",hp_inimigo: 0";
			return ResponseEntity.ok(json);
		}
		bt.getPokemonSelvagem().setHp_atual(w.getHp_atual()-dano);
		w.setHp_atual(w.getHp_atual()-dano);
		p.get().setStamina_atual(p.get().getStamina_atual()+1);
		var json = "dano:"+dano+",hp_inimigo: "+w.getHp_atual()+",stamina_atual:"+p.get().getStamina_atual();
		wpms.salvar(w);
		salvar(bt);
		pmus.salvar(p.get());
		return ResponseEntity.ok(json);
	}
	
}
