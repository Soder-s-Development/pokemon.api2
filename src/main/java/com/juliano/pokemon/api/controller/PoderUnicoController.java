package com.juliano.pokemon.api.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.pokemon.api.Model.PoderUnico;
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
	public ResponseEntity<HashMap> getMeusPoderes(@PathVariable Long id1, @PathVariable Long id2, @PathVariable Long id3, @PathVariable Long id4) {
		HashMap<String, Object> map = new HashMap<>();
		PoderUnico pdu = null;
		System.out.println("Montando map");
		if(id1 == 0){
			return ResponseEntity.notFound().build();
		}
		var pduO = pdur.findById(id1);
		if(pduO.isEmpty()){
			return ResponseEntity.notFound().build();
		}else{
			pdu = pduO.get();
		}
		if (pdu.getId() > 0) {
			map.put("poderUnico1", pdu);
			map.put("poder1", pr.findById(pdu.getId_power()));
			pdu = null;
		}
		if(id2 > 0){
			pdu = pdur.findById(id2).get();
		}
		if (pdu != null) {
			map.put("poderUnico2", pdu);
			map.put("poder2", pr.findById(pdu.getId_power()));
			pdu = null;
		}
		if(id3 > 0){
			pdu = pdur.findById(id3).get();
		}
		if (pdu != null) {
			map.put("poderUnico3", pdu);
			map.put("poder3", pr.findById(pdu.getId_power()));
			pdu = null;
		}
		if(id4 > 0){
			pdu = pdur.findById(id4).get();
		}
		if (pdu != null) {
			map.put("poderUnico4", pdu);
			map.put("poder4", pr.findById(pdu.getId_power()));
			pdu = null;
		}
		return ResponseEntity.status(200).body(map);
	}
}
