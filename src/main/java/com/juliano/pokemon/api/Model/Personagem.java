package com.juliano.pokemon.api.Model;

import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Personagem {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Long id_conta;
	@NotNull
	@NotBlank
	private String nome;
	private Set<Long> pkmu_ids;
	private Set<Long> hold_ids;
	
	public int getPartyLength() {
		return this.hold_ids.size();
	}
	
	public boolean hasSpaceInParty() {
		return this.hold_ids.size() < 6;
	}
	
	public Set<Long> setPokemonIntoParty(Long id) {
		if(hasSpaceInParty()) {
			hold_ids.add(id);
			pkmu_ids.add(id);
		}else {
			pkmu_ids.add(id);
		}
		return this.pkmu_ids;
	}
}
