package com.juliano.pokemon.api.Model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pokemon {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@Column(name = "nivel")
	private int level;
	private int HP;
	private int Atk;
	private int Def;
	private int SpA;
	private int SpD;
	private int Spe;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
	public int getAtk() {
		return Atk;
	}
	public void setAtk(int atk) {
		Atk = atk;
	}
	public int getDef() {
		return Def;
	}
	public void setDef(int def) {
		Def = def;
	}
	public int getSpA() {
		return SpA;
	}
	public void setSpA(int spA) {
		SpA = spA;
	}
	public int getSpD() {
		return SpD;
	}
	public void setSpD(int spD) {
		SpD = spD;
	}
	public int getSpe() {
		return Spe;
	}
	public void setSpe(int spe) {
		Spe = spe;
	}
	@Override
	public int hashCode() {
		return Objects.hash(Atk, Def, HP, SpA, SpD, Spe, id, level, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		return Atk == other.Atk && Def == other.Def && HP == other.HP && SpA == other.SpA && SpD == other.SpD
				&& Spe == other.Spe && Objects.equals(id, other.id) && level == other.level
				&& Objects.equals(nome, other.nome);
	}
	
	
}