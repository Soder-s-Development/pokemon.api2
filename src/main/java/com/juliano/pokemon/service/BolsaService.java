package com.juliano.pokemon.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.juliano.pokemon.api.Converter.Converter;
import com.juliano.pokemon.api.Model.Bolsa;
import com.juliano.pokemon.api.Model.Item;
import com.juliano.pokemon.repository.BolsaRepository;
import com.juliano.pokemon.repository.ItemRepository;
import com.juliano.pokemon.response.BolsaResponse;

import javassist.NotFoundException;

import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class BolsaService {

	@Autowired
	private BolsaRepository bolsaRepository;
	
	private ItemRepository itemRepository;
	
	public Bolsa criarBolsa(Bolsa bolsa) {
		
		return bolsaRepository.save(bolsa);
	
	}
	
	public List<Item> atualizarItem(Long id, Long itemId) throws NotFoundException {
		
		Item item = Optional.ofNullable(itemRepository.findById(itemId).get())
				.orElseThrow(() -> new NotFoundException("Item não identificado"));
		
		Bolsa b = bolsaRepository.getById(id);
		
		b.saveNewItem(item);
		
		return b.getAllItens();
	
	}

	public BolsaResponse pegarMinhaBolsaCompleta(Long id) throws NotFoundException{
		return Converter.from(
				Optional.ofNullable(bolsaRepository.findById(id).get())
				.orElseThrow(() -> new NotFoundException("Bolsa não encontrada ou inixistente")));
	}
}
