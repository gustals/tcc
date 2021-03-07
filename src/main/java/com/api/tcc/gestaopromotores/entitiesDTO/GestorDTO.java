package com.api.tcc.gestaopromotores.entitiesDTO;

public class GestorDTO {
	
	private String nome;
	private int matricula;
	
	public GestorDTO() {
	}
	
	public GestorDTO(String nome, int matricula) {
		super();
		this.nome = nome;
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

}
