package com.juliano.pokemon.api.controller;

import com.juliano.pokemon.api.Model.*;
import com.juliano.pokemon.repository.PoderUnicoRepository;
import com.juliano.pokemon.service.*;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/batalha")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost", "http://127.0.0.1", "http://0.0.0.0", "x-requested-with", "content-type"}, originPatterns = "*")
public class BatalhaController {

	@Autowired
	private BatalhaService btservice;
	@Autowired
	private PokemonService pkms;
	@Autowired
	private WildPokemonService wpms;
	@Autowired
	private PokemonUnicoService pmus;
	@Autowired
	private PoderUnicoRepository pdrur;
	@Autowired
	private PokemonPoderService poderService;

	@PostMapping("/{id1}/{id2}")
	public ResponseEntity<Any> iniciarBatalha(@PathVariable Long id1, @PathVariable Long id2) {
		return btservice.iniciarBatalha(id1, id2);
	}

	@GetMapping("/foundPokemon/{level}")
	public ResponseEntity<WildPokemon> foundAPokemon(@PathVariable int level) {
		Random rand = new Random();
		int int_random = rand.nextInt(150);
		return  ResponseEntity.ok(wpms.genereteWildP((long)int_random, level));
	}

	@PostMapping("/attack/{id1}/wild/{id2}/power/{idPU}/{btid}")
	public ResponseEntity Attack(@PathVariable Long id1, @PathVariable Long id2, @PathVariable Long idPU, @PathVariable Long btid){
		if(id1 < 1 || id2 < 1 || id2 < 1 || idPU < 1 || btid < 1){
			ResponseEntity.status(400)
					.body("Parâmetros inválidos na requisição");
		}
		var p = pmus.getPokemon(id1);
		p = p != null ? p : null;
		WildPokemon w = wpms.getWild(id2);
		PoderUnico pu = pdrur.findById(idPU).get();
		PokemonPoder pp = poderService.getPoder(pu.getId_power());
		Batalha bt = btservice.getBatalha(btid).isPresent() ? btservice.getBatalha(btid).get() : null;
		if(bt == null) return ResponseEntity.ok("There is no battle running");
		if (!p.isPresent() || !p.get().podeAtacar(pp)){
			p.get().setStamina_atual(p.get().getStamina_atual()+1);
			pmus.salvar(p.get());
			return ResponseEntity.ok("Can't attack");
		}
		int dano = poderService.calculaDano(pkms.getPokemon(p.get().getId_pokemon()), pkms.getPokemon(w.getId_pokemon()), pp);
		dano = dano+(pu.getLevel()*30);

		if((w.getHp_atual()-dano)<1){
			w.setHp_atual(0);
			bt.setVida_p7(0);
			wpms.salvar(w);
			btservice.save(bt);
			var json = "dano:"+dano+",hp_inimigo: 0";
			return ResponseEntity.ok(json);
		}
		bt.setVida_p7(w.getHp_atual()-dano);
		w.setHp_atual(w.getHp_atual()-dano);
		p.get().setStamina_atual(p.get().getStamina_atual()+1);
		var json = "dano:"+dano+",hp_inimigo: "+w.getHp_atual()+",stamina_atual:"+p.get().getStamina_atual();
		wpms.salvar(w);
		btservice.save(bt);
		pmus.salvar(p.get());
		return ResponseEntity.ok(json);
	}

	@PostMapping("/wildAttack/{id1}/{id2}/power/{idp}/{btid}")
	public ResponseEntity wildAttack(@PathVariable Long id1, @PathVariable Long id2, @PathVariable Long idp, @PathVariable Long btid){
		if ( idp < 1 ) idp = Long.valueOf(1);
		if(id1 < 1 || id2 < 1 || id2 < 1 || btid < 1){
			ResponseEntity.status(400)
					.body("Parâmetros inválidos na requisição");
		}
		Optional<PokemonUnico> pu = pmus.getPokemon(id2);
		if ( !pu.isPresent() ){
			ResponseEntity.notFound().build();
		}
		PokemonUnico p = pu.get();
		WildPokemon w = wpms.getWild(id1);
		PokemonPoder pp = poderService.getPoder(idp);
		Batalha bt = btservice.getBatalha(btid).isPresent() ? btservice.getBatalha(btid).get() : null;

		if(bt == null){
			return ResponseEntity.ok("Não tem batalha registrada");
		}
		if(w == null){
			return ResponseEntity.status(404).body("Pokemon Selvagem não existe");
		}
		if(pp == null){
			return ResponseEntity.status(404).body("Poder não existe");
		}
		int dano = poderService.calculaDano(pkms.getPokemon(w.getId_pokemon()), pkms.getPokemon(p.getId_pokemon()), pp);
		if((p.getHp_atual()-dano)<1){
			p.setHp_atual(0);
			bt.setVida_p1(0);
			pmus.salvar(p);
			btservice.save(bt);
			var res = "dano:"+dano+",hp_pokemon: 0";
			return ResponseEntity.ok(res);
		}
		bt.setVida_p1(p.getHp_atual()-dano);
		p.setHp_atual(p.getHp_atual()-dano);
		String res = "dano:"+dano+",hp_pokemon: "+p.getHp_atual();
		pmus.salvar(p);
		btservice.save(bt);
		pmus.salvar(p);
		return ResponseEntity.ok(res);
	}
}
