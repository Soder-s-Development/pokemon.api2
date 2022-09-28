package com.juliano.pokemon.api.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.juliano.pokemon.repository.ItemRepository;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Bolsa {
	
	@Transient
	@Autowired
	private ItemRepository itemRepository;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Long idPersonagem;
	
	private Long idContaAssociada;
	
	//coin scoins pokebola racao

	public List<Item> getAllItens() {
		return itemRepository.findAllByIdProprietario(this.idPersonagem);
	}
	
	public Item saveNewItem(Item item) {
		return itemRepository.save(new Item().builder()
		.idProprietario(idPersonagem)
		.idContaAssociada(idContaAssociada)
		.nome(item.getNome())
		.descricao(item.getDescricao())
		.valorDeCompra(item.getValorDeCompra())
		.valorDeVenda(item.getValorDeVenda())
		.build());
	}
}
