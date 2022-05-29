package com.juliano.pokemon.api.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@Table(name="wild_pokemon")
public class WildPokemon {

	@NotNull
	private String tipo;
	private int nivel;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Long id_pokemon;
	@NotNull
	private String nome;
	private int novo_hp;
	private int novo_atk;
	private int novo_def;
	private int novo_spa;
	private int novo_spd;
	private int novo_spe;

	private boolean vivo = true;
	private String genero;
	private Long poder1;
	private Long poder2;
	private Long poder3;
	private Long poder4;

	private int hp_atual;
	private int stamina;
}
