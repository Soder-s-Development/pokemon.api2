package com.juliano.pokemon.api.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="poder_unico")
public class PoderUnico {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long id_pokemon_unico;
	private Long id_wild_pokemon;
	@NotNull
	@Column(name="id_poder")
	private Long id_power;
	private int level;
	private String some_effect; 
	private boolean ativo;
	
	public boolean isAtivo() {
		return this.ativo;
	}
}
