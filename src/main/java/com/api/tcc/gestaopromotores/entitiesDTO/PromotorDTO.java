package com.api.tcc.gestaopromotores.entitiesDTO;

public class PromotorDTO {
	
	private String nome;
	private int matricula;
	private Long gestorId;
	
	public PromotorDTO() {
	}
	
	public PromotorDTO(String nome, int matricula, Long gestorId) {
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.gestorId=gestorId;
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

	public Long getGestorId() {
		return gestorId;
	}

	public void setGestorId(Long gestorId) {
		this.gestorId = gestorId;
	}
	
	
}
