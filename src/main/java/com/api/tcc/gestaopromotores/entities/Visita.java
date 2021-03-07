package com.api.tcc.gestaopromotores.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="tb_visitas")
public class Visita implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne(optional = false)
	private Gestor gestorResponsavel;
	
	
	@ManyToOne(optional = false)
	private Promotor promotor;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "dd-MM-yyyy", iso = ISO.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataVisita;
	
	@Column(nullable = false)
	private String nomeLocalVisita;
	@Column(nullable = false)
	private String localizacaoLatitude;
	@Column(nullable = false)
	private String localizacaoLongitude;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime dataEHoraInicioVista;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime dataEHoraFimVista;
	
	//registrado pelo supervisor
	private String atividadesPrevistas;
	private ArrayList<String> imagensVisita;
	
	private String atividadesRealizadas;	
	private HashMap<String, String> estoqueRepresentado;
	private HashMap<String, String> precosConcorrencia;
	
	public Visita() {
		
	}
	
	public Visita(Gestor gestorResponsavel, Promotor promotor, String nomeLocalVisita,
			String localizacaoLatitude, String localizacaoLongitude, String atividadesPrevistas, LocalDate dataVisita) {
		super();
		this.gestorResponsavel = gestorResponsavel;
		this.promotor = promotor;
		this.nomeLocalVisita = nomeLocalVisita;
		this.localizacaoLatitude = localizacaoLatitude;
		this.localizacaoLongitude = localizacaoLongitude;
		this.atividadesPrevistas = atividadesPrevistas;
		this.dataVisita = dataVisita;
		
		this.estoqueRepresentado = new HashMap<String, String>();
		this.precosConcorrencia = new HashMap<String, String>();
		this.imagensVisita=new ArrayList<String>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Gestor getGestorResponsavel() {
		return gestorResponsavel;
	}

	public void setGestorResponsavel(Gestor gestorResponsavel) {
		this.gestorResponsavel = gestorResponsavel;
	}

	public Promotor getPromotor() {
		return promotor;
	}

	public void setPromotor(Promotor promotor) {
		this.promotor = promotor;
	}

	public String getNomeLocalVisita() {
		return nomeLocalVisita;
	}

	public void setNomeLocalVisita(String nomeLocalVisita) {
		this.nomeLocalVisita = nomeLocalVisita;
	}

	public String getLocalizacaoLatitude() {
		return localizacaoLatitude;
	}

	public void setLocalizacaoLatitude(String localizacaoLatitude) {
		this.localizacaoLatitude = localizacaoLatitude;
	}

	public String getLocalizacaoLongitude() {
		return localizacaoLongitude;
	}

	public void setLocalizacaoLongitude(String localizacaoLongitude) {
		this.localizacaoLongitude = localizacaoLongitude;
	}

	public LocalDateTime getDataEHoraInicioVista() {
		return dataEHoraInicioVista;
	}

	public void setDataEHoraInicioVista(LocalDateTime dataEHoraInicioVista) {
		this.dataEHoraInicioVista = dataEHoraInicioVista;
	}

	public LocalDateTime getDataEHoraFimVista() {
		return dataEHoraFimVista;
	}

	public void setDataEHoraFimVista(LocalDateTime dataEHoraFimVista) {
		this.dataEHoraFimVista = dataEHoraFimVista;
	}

	public String getAtividadesPrevistas() {
		return atividadesPrevistas;
	}

	public void setAtividadesPrevistas(String atividadesPrevistas) {
		this.atividadesPrevistas = atividadesPrevistas;
	}

	public String getAtividadesRealizadas() {
		return atividadesRealizadas;
	}

	public void setAtividadesRealizadas(String atividadesRealizadas) {
		this.atividadesRealizadas = atividadesRealizadas;
	}

	public HashMap<String, String> getEstoqueRepresentado() {
		return estoqueRepresentado;
	}

	public void setEstoqueRepresentado(HashMap<String, String> estoqueRepresentado) {
		this.estoqueRepresentado = estoqueRepresentado;
	}

	public HashMap<String, String> getPrecosConcorrencia() {
		return precosConcorrencia;
	}

	public void setPrecosConcorrencia(HashMap<String, String> precosConcorrencia) {
		this.precosConcorrencia = precosConcorrencia;
	}

	public ArrayList<String> getImagensVisita() {
		return imagensVisita;
	}

	public void addImagenVisita(ArrayList<String> imagensVisita) {
		this.imagensVisita = imagensVisita;
	}

	public LocalDate getDataVisita() {
		return dataVisita;
	}

	public void setDataVisita(LocalDate dataVisita) {
		this.dataVisita = dataVisita;
	}

	
}
