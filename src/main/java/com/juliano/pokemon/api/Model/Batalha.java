package com.juliano.pokemon.api.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Getter
@Setter
@Table(name = "batalhas")
public class Batalha {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Long id_conta1;
	@NotNull
	private Long id_conta2;
	
	private Long id_pokemon1;
	private int vida_p1;
	private Long id_pokemon2;
	private int vida_p2;
	private Long id_pokemon3;
	private int vida_p3;
	private Long id_pokemon4;
	private int vida_p4;
	private Long id_pokemon5;
	private int vida_p5;
	private Long id_pokemon6;
	private int vida_p6;
	
	private Long id_pokemon7;
	private int vida_p7;
	private Long id_pokemon8;
	private int vida_p8;
	private Long id_pokemon9;
	private int vida_p9;
	private Long id_pokemon10;
	private int vida_p10;
	private Long id_pokemon11;
	private int vida_p11;
	private Long id_pokemon12;
	private int vida_p12;
}
