package com.juliano.pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juliano.pokemon.api.Model.PokemonUnico;

@Repository
public interface PokemonUnicoRepository extends JpaRepository<PokemonUnico, Long>{

}
