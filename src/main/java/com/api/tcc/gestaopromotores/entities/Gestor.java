package com.api.tcc.gestaopromotores.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="tb_gestor")
public class Gestor {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private int matricula;
	
	@JsonIgnore
	@OneToMany(mappedBy = "gestor")
	private List<Promotor> promotoresSupervisionados;
	
	
	public Gestor() {
		
	}
	
	public Gestor(String nome, int matricula) {
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.promotoresSupervisionados = new ArrayList<Promotor>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setName(String nome) {
		this.nome = nome;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public List<Promotor> getPromotoresSupervisionados() {
		return promotoresSupervisionados;
	}

	public void setPromotoresSupervisionados(List<Promotor> promotoresSupervisionados) {
		this.promotoresSupervisionados = promotoresSupervisionados;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gestor other = (Gestor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
