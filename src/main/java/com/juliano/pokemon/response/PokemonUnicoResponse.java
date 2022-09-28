package com.juliano.pokemon.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PokemonUnicoResponse {

	private String tipo;
	private String apelido;
	private int nivel;
	
	private Long id;
	private Long id_pokemon;
	
	private String nome_pokemon;
	
	private Long personagem_id;
	
	
	private int novo_hp;
	private int novo_atk;
	private int novo_def;
	private int novo_spa;
	private int novo_spd;
	private int novo_spe;
	
	private int dias_de_vida = 999;
	private int dias_vivido = 0;
	private String conquistas;
	private String crias;
	private boolean vivo = true;
	private String genero;
	
	private int hp_atual;
	private int stamina;
	private int stamina_atual;
	private int evoluvao_estado;
	
	private List<PoderesResponse> poderes;
}
