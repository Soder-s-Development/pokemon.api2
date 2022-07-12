package com.juliano.pokemon.api.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.juliano.pokemon.service.PokemonPoderService;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
public class PokemonUnico{
	
	@NotNull
	private String tipo;
	private String apelido;
	private int nivel;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Long id_pokemon;
	
	@NotNull
	private String nome_pokemon;
	
	@NotNull
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
	
	private Long poder1;
	private Long poder2;
	private Long poder3;
	private Long poder4;
	
	private int adicional_poder1;
	private int adicional_poder2;
	private int adicional_poder3;
	private int adicional_poder4;

	private int hp_atual;
	private int stamina;
	private int stamina_atual;
	private int evoluvao_estado;
	
	public PokemonUnico(Pokemon pkm, String apelido, Long personagemid) {
		this.nivel = 1;
		this.apelido = apelido;
		this.personagem_id = personagemid;
		this.tipo = pkm.getTipo();
		this.id_pokemon = pkm.getId();
		this.novo_hp = pkm.getHp()+(int)Math.floor(Math.random()*(10-0+1)+0);
		this.novo_atk = pkm.getAtk()+(int)Math.floor(Math.random()*(10-0+1)+0);
		this.novo_def = pkm.getDef()+(int)Math.floor(Math.random()*(10-0+1)+0);
		this.novo_spa = pkm.getSpa()+(int)Math.floor(Math.random()*(10-0+1)+0);
		this.novo_spd = pkm.getSpd()+(int)Math.floor(Math.random()*(10-0+1)+0);
		this.novo_spe = pkm.getSpe()+(int)Math.floor(Math.random()*(10-0+1)+0);
		this.hp_atual = pkm.getHp()*10;
		this.stamina = pkm.getHp()+pkm.getSpe();
		this.stamina_atual = stamina;
		this.nome_pokemon = pkm.getNome();
		this.evoluvao_estado = pkm.getEstado();
		
		if((int)Math.floor(Math.random()*(10-0+1)+0)%2 == 0) {
			this.genero = "M";
		}else {
			this.genero = "F";
		}
		
		System.out.println("pokemon capturado! Apelido -> "+this.apelido);
	}
	public void evoluir(PokemonUnico pkmunico, Pokemon pkm){
		this.tipo = pkm.getTipo();
		this.id_pokemon = pkm.getId();
		this.nome_pokemon = pkm.getNome();

		if(this.novo_hp-pkm.getHp()>0){
			this.novo_hp = (this.novo_hp-pkm.getHp())+pkm.getHp()+(pkm.getHp()-pkmunico.getNovo_hp());
		}else {
			this.novo_hp = pkm.getHp()+(pkm.getHp()-pkmunico.getNovo_hp());
		}
		if(this.novo_atk-pkm.getAtk()>0){
			this.novo_atk = (this.novo_atk-pkm.getAtk())+pkm.getAtk()+(int)Math.floor(Math.random()*(10-0+1)+0);
		}else{
			this.novo_atk = pkm.getAtk()+(int)Math.floor(Math.random()*(10-0+1)+0);
		}
		if(this.novo_def-pkm.getDef()>0){
			this.novo_def = (this.novo_def-pkm.getDef())+pkm.getDef()+(int)Math.floor(Math.random()*(10-0+1)+0);
		}else{
			this.novo_def = pkm.getDef()+(int)Math.floor(Math.random()*(10-0+1)+0);
		}
		if(this.novo_spa-pkm.getSpa()>0){
			this.novo_spa = (this.novo_spa-pkm.getSpa())+pkm.getSpa()+(int)Math.floor(Math.random()*(10-0+1)+0);
		}else{
			this.novo_spa = pkm.getSpa()+(int)Math.floor(Math.random()*(10-0+1)+0);
		}
		if(this.novo_spd-pkm.getSpd()>0){
			this.novo_spd = (this.novo_spd-pkm.getSpd())+pkm.getSpd()+(int)Math.floor(Math.random()*(10-0+1)+0);
		}else{
			this.novo_spd = pkm.getSpd()+(int)Math.floor(Math.random()*(10-0+1)+0);
		}
		if(this.novo_spe-pkm.getSpe()>0){
			this.novo_spe = (this.novo_spe-pkm.getSpe())+pkm.getSpe()+(int)Math.floor(Math.random()*(10-0+1)+0);
		}else{
			this.novo_spe = pkm.getSpe()+(int)Math.floor(Math.random()*(10-0+1)+0);
		}

		this.evoluvao_estado++;
		System.out.println("pokemon evoluído para op estágio"+this.evoluvao_estado +"! Nome -> "+this.getNome_pokemon());
	}
	
	public Boolean podeAtacar(PokemonPoder poder) {
		if(poder.getDanobase() > this.getStamina()) {
			return false;
		}
		this.setStamina(this.getStamina()-poder.getDanobase());
		return true;
	}
}
