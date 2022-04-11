package com.juliano.pokemon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliano.pokemon.api.Model.Batalha;
import com.juliano.pokemon.repository.BatalhaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BatalhaService {

	@Autowired
	private BatalhaRepository br;
	
	// em construção
	public Batalha iniciarBatalha(long idconta1, long idconta2) {
		Batalha bt = new Batalha();
		bt.setId_conta1(idconta1);
		bt.setId_conta2(idconta2);
		return br.save(bt);
	}
}
