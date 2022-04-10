package com.juliano.pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juliano.pokemon.api.Model.PokemonExperiencia;

@Repository
public interface PokemonExperienciaRepository extends JpaRepository<PokemonExperiencia, Long>{

}
