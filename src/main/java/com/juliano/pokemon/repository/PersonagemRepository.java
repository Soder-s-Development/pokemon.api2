package com.juliano.pokemon.repository;

import com.juliano.pokemon.api.Model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    @Query("select p from Personagem p where p.id_conta = :id_conta")
    Personagem findById_conta(@Param("id_conta") Long id_conta);
    List<Personagem> findByNome(String nome);
}
