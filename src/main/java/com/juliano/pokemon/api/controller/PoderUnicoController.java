package com.juliano.pokemon.api.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.pokemon.api.Model.PoderUnico;
import com.juliano.pokemon.api.Model.PokemonPoder;
import com.juliano.pokemon.repository.PoderRepository;
import com.juliano.pokemon.repository.PoderUnicoRepository;
import com.juliano.pokemon.service.PokemonUnicoService;

import lombok.AllArgsConstructor;

@ComponentScan
@RestController
@RequestMapping("/poderunico")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost", "http://127.0.0.1", "http://0.0.0.0", "x-requested-with", "content-type"}, originPatterns = "*")
public class PoderUnicoController {

	@Autowired
	private PoderUnicoRepository pdur;
	@Autowired
	private PoderRepository pr;
	@Autowired
	private PokemonUnicoService pkus;
	
	@PostMapping("/{id}/{id_pkm}")
	@ResponseStatus(HttpStatus.CREATED)
	public Object aprendePoder(@PathVariable Long id, @PathVariable Long id_pkm) {
		System.out.println("Aprendendo poder");
		PoderUnico pu = new PoderUnico();
		pu.setId_pokemon_unico(id_pkm);
		pu.setId_power(id);
		pu.setLevel(1);
		pu.setSome_effect("");
		var npu = pdur.save(pu);
		pkus.aprenderPoderPU(npu);
		return npu;
	}
	
	@GetMapping("/{id1}/{id2}/{id3}/{id4}")
	public ArrayList<Object> getMeusPoderes(@PathVariable Long id1, @PathVariable Long id2, @PathVariable Long id3, @PathVariable Long id4) {
		var poderes = new ArrayList<>();
		System.out.println("Montando lista");
		if(id1>0) {
			var poder = new HashMap<>();
			var ob = pdur.findById(id1).get();
			PokemonPoder pd;
			pd = pr.findById(ob.getId_power()).get();
			poder.put("nome", pd.getNome());
			poder.put("tipo", pd.getTipo());
			poder.put("efeito", pd.getEfeito());
			poder.put("descricao", pd.getDescricao());
			poder.put("dano", pd.getDanobase());
			poder.put("buff_pu", ob.getSome_effect());
			
			poderes.add(poder);
		}
		if(id2>0) {
			var poder = new HashMap<>();
			var ob = pdur.findById(id2).get();
			PokemonPoder pd;
			pd = pr.findById(ob.getId_power()).get();
			poder.put("nome", pd.getNome());
			poder.put("tipo", pd.getTipo());
			poder.put("efeito", pd.getEfeito());
			poder.put("descricao", pd.getDescricao());
			poder.put("dano", pd.getDanobase());
			poder.put("buff_pu", ob.getSome_effect());
			
			poderes.add(poder);
		}
		if(id3>0) {
			var poder = new HashMap<>();
			var ob = pdur.findById(id3).get();
			PokemonPoder pd;
			pd = pr.findById(ob.getId_power()).get();
			poder.put("nome", pd.getNome());
			poder.put("tipo", pd.getTipo());
			poder.put("efeito", pd.getEfeito());
			poder.put("descricao", pd.getDescricao());
			poder.put("dano", pd.getDanobase());
			poder.put("buff_pu", ob.getSome_effect());
			
			poderes.add(poder);
		}
		if(id4>0) {
			var poder = new HashMap<>();
			var ob = pdur.findById(id4).get();
			PokemonPoder pd;
			pd = pr.findById(ob.getId_power()).get();
			poder.put("nome", pd.getNome());
			poder.put("tipo", pd.getTipo());
			poder.put("efeito", pd.getEfeito());
			poder.put("descricao", pd.getDescricao());
			poder.put("dano", pd.getDanobase());
			poder.put("buff_pu", ob.getSome_effect());
			
			poderes.add(poder);
		}
		
		return poderes;
	}
}
