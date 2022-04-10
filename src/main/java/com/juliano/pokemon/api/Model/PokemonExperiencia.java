package com.juliano.pokemon.api.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="pku_experiencias")
public class PokemonExperiencia {

	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private long id_pokemon_unico;
	private String titulos;
	private int experiencia;
	private int batalhas_vencidas;
	private int batalhas_derrotas;
	

	public PokemonExperiencia(long idu) {
		this.id_pokemon_unico = idu;
		this.batalhas_vencidas = 0;
		this.batalhas_derrotas = 0;
		this.experiencia = 0;
	}
}
