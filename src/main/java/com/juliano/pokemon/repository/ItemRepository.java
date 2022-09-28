package com.juliano.pokemon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juliano.pokemon.api.Model.Item;
import com.juliano.pokemon.api.Model.Personagem;

public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findAllByIdProprietario(Long idProprietario);
}
