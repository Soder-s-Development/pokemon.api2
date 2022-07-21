package com.juliano.pokemon.service;

import javax.validation.Valid;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.juliano.pokemon.api.Model.Bolsa;
import com.juliano.pokemon.repository.BolsaRepository;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class BolsaService {

	@Autowired
	private BolsaRepository blsr;
	
	public Bolsa criarBolsa(Bolsa bolsa) {
		return blsr.save(bolsa);
	}
	public Bolsa atualizarItem(Long id, String s) {
		Bolsa b = blsr.getById(id);
		
		if(s.isBlank() || s.isBlank())
			new Exception("no string");

		if(b.getItens() != null) {
			if(this.isItemSet(s, b)) {
				return this.setCurrentString(b, s);
			}else {
				String itens = b.getItens();
				itens = itens+";"+s;
				b.setItens(itens);
				return blsr.save(b);
			}
		}
		
		String itens = s;
		b.setItens(itens);
		return blsr.save(b);
	}
	
	private boolean isItemSet(String s, Bolsa b) {
		if(b.getItens() != null) {
			if(b.getItens().contains(s.split(":")[0]))
				return true;
		}
		return false;
	}
	private Bolsa setCurrentString(Bolsa b, String s) {
		String[] sa = b.getItens().split(";");
		String newObject = s.split(":")[0];
		System.out.println("newobject "+newObject);
		for(int i = 0; i<sa.length; i++) {
			String object = sa[i].split(":")[0];
			System.out.println("object "+object);
			if(object.contains(newObject)) {
				sa[i] = s;
				System.out.println(sa[i]+ " atualizado");
			}
		}
		String newArrayOfObjects = "";
		int i = 0;
		for (String string : sa) {
			if(i==0) {
				newArrayOfObjects += string;
				i++;
			}else {
				newArrayOfObjects += ";"+string;
				i++;
			}
			
		}
		b.setItens(newArrayOfObjects);
		return blsr.save(b);
	}
	public ResponseEntity<Bolsa> getBolsa(Long id) {
		return this.blsr.findById(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
