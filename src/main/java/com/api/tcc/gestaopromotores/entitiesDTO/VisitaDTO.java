package com.api.tcc.gestaopromotores.entitiesDTO;


import java.text.ParseException;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;


public class VisitaDTO {

	private Long IdGestorResponsavel;
	private Long IdPromotor;
	private String nomeLocalVisita;
	private String localizacaoLatitude;
	private String localizacaoLongitude;	
	private String atividadesPrevistas;
	
	@ApiModelProperty(
			  value = "Data da vista",
			  name = "dataVisita",
			  dataType = "LocalDate",
			  example = "dd-mm-aaaa")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataVisita;
		
	public VisitaDTO() {
		
	}
	
	public VisitaDTO(Long IdGestorResponsavel, Long IdPromotor, String nomeLocalVisita,
			String localizacaoLatitude, String localizacaoLongitude, String atividadesPrevistas, LocalDate dataVisita) throws ParseException {
		super();

		this.IdGestorResponsavel = IdGestorResponsavel;
		this.IdPromotor = IdPromotor;
		this.nomeLocalVisita = nomeLocalVisita;
		this.localizacaoLatitude = localizacaoLatitude;
		this.localizacaoLongitude = localizacaoLongitude;
		this.atividadesPrevistas = atividadesPrevistas;
		this.dataVisita= dataVisita;
	}
	
	
	public LocalDate getDataVisita() {
		return dataVisita;
	}

	public void setDataVisita(LocalDate dataVisita) {
		this.dataVisita = dataVisita;
	}

	public Long getGestorResponsavel() {
		return IdGestorResponsavel;
	}

	public void setGestorResponsavel(Long gestorResponsavel) {
		this.IdGestorResponsavel = gestorResponsavel;
	}

	public Long getPromotor() {
		return IdPromotor;
	}

	public void setPromotor(Long promotor) {
		this.IdPromotor = promotor;
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

	public String getAtividadesPrevistas() {
		return atividadesPrevistas;
	}

	public void setAtividadesPrevistas(String atividadesPrevistas) {
		this.atividadesPrevistas = atividadesPrevistas;
	}	

	
}
