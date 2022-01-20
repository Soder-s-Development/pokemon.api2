package com.juliano.pokemon.api.Model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PokemonUnico{
    
	private String nome;
	private String apelido;
	private int id_pokemon_unico;
	
	@NotNull
	private String tipo;
	private int hp;
	private int atk;
	private int def;
	private int spa;
	private int spd;
	private int spe;
	
	
	public void setAllNewValues(Pokemon pkm, String apelido) {
		this.nome = pkm.getNome();
		this.apelido = apelido;
		this.tipo = pkm.getTipo();
		this.hp = pkm.getHp()+(int)Math.floor(Math.random()*(10-0+1)+0);
		this.atk = pkm.getAtk()+(int)Math.floor(Math.random()*(10-0+1)+0);
		this.def = pkm.getDef()+(int)Math.floor(Math.random()*(10-0+1)+0);
		this.spa = pkm.getSpa()+(int)Math.floor(Math.random()*(10-0+1)+0);
		this.spd = pkm.getSpd()+(int)Math.floor(Math.random()*(10-0+1)+0);
		this.spe = pkm.getSpe()+(int)Math.floor(Math.random()*(10-0+1)+0);
		
		System.out.println("pokemon capturado! "+this.apelido);
	}
	
}
