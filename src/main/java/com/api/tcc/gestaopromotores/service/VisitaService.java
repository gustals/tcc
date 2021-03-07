package com.api.tcc.gestaopromotores.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.tcc.gestaopromotores.entities.Gestor;
import com.api.tcc.gestaopromotores.entities.Promotor;
import com.api.tcc.gestaopromotores.entities.Visita;
import com.api.tcc.gestaopromotores.entitiesDTO.VisitaDTO;
import com.api.tcc.gestaopromotores.exception.RegraDeNegocioException;
import com.api.tcc.gestaopromotores.repository.VisitaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


@Service
public class VisitaService {

	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(VisitaService.class);
	
	@Autowired
	private VisitaRepository repository;
	
	@Autowired
	private PromotorService promotorService;
	
	@Autowired
	private GestorService gestorService;
	
	
	public List<Visita> findAll(){
		return repository.findAll();
	}
	
	public Visita findById(Long id){
		if(!repository.findById(id).isPresent()) {
			throw new RegraDeNegocioException("Visita não existe");
		}
		return repository.findById(id).get();
	}
	
	public List<Visita> buscarPorPromotorMesAno(Long id, String dataString){
		//verifica se o promotor existe
		promotorService.findById(id);
		
		if(Integer.parseInt(dataString.substring(0,2))>12 || Integer.parseInt(dataString.substring(0,2))<1) {
			LOGGER.info(""+Integer.parseInt(dataString.substring(0,2)));
			throw new RegraDeNegocioException("Mês informado não existe");
		}
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
		LocalDate inicioMes = LocalDate.parse("01-"+dataString, formato);
		LocalDate fimMes = LocalDate.parse("31-"+dataString, formato);
		
		if(inicioMes.getYear()>LocalDate.now().getYear()) {
			LOGGER.info(""+Integer.parseInt(dataString.substring(3,6)));
			throw new RegraDeNegocioException("Ano informado não existe");
		}
		
		return repository.findByPromotorIdAndDataVisitaBetween(id, inicioMes, fimMes);
	}
	
	public List<Visita> buscarPorGestorMesAno(Long id, String dataString){
		//verifica se o gestor existe
		gestorService.findById(id);
		
		if(Integer.parseInt(dataString.substring(0,2))>12 || Integer.parseInt(dataString.substring(0,2))<1) {
			LOGGER.info(""+Integer.parseInt(dataString.substring(0,2)));
			throw new RegraDeNegocioException("Mês informado não existe");
		}
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
		LocalDate inicioMes = LocalDate.parse("01-"+dataString, formato);
		LocalDate fimMes = LocalDate.parse("31-"+dataString, formato);
		
		if(inicioMes.getYear()>LocalDate.now().getYear()) {
			LOGGER.info(""+Integer.parseInt(dataString.substring(3,6)));
			throw new RegraDeNegocioException("Ano informado não existe");
		}
		
		return repository.findByGestorResponsavelIdAndDataVisitaBetween(id, inicioMes, fimMes);
	}
	
	public Visita criarVisita(VisitaDTO novaVisita) throws ParseException{	
		Gestor gestor = gestorService.findById(novaVisita.getGestorResponsavel());		
		Promotor promotor = promotorService.findById(novaVisita.getPromotor());
		
		if(novaVisita.getDataVisita().isBefore(LocalDate.now())) {
			throw new RegraDeNegocioException("Data inferior ao dia de hoje");
		}
		Visita objVisita = new Visita(gestor, promotor,	novaVisita.getNomeLocalVisita(), novaVisita.getLocalizacaoLatitude(), 
					novaVisita.getLocalizacaoLongitude(), novaVisita.getAtividadesPrevistas(), novaVisita.getDataVisita());	
		repository.save(objVisita);		
		return objVisita;
	}
	
	public Visita alterarVisitaGestor(Long id, VisitaDTO novaVisita) throws ParseException{	
		Gestor gestor = gestorService.findById(novaVisita.getGestorResponsavel());		
		Promotor promotor = promotorService.findById(novaVisita.getPromotor());
			
		Visita visita = this.findById(id);
		
		if (visita.getDataEHoraInicioVista()!=null) { 
			throw new RegraDeNegocioException("Visita já foi iniciada não pode ser alterada" );	
		}
		
		if(novaVisita.getDataVisita().isBefore(LocalDate.now())) {
			throw new RegraDeNegocioException("Data inferior ao dia de hoje");
		}
		
		visita.setAtividadesPrevistas(novaVisita.getAtividadesPrevistas());
		visita.setDataVisita(novaVisita.getDataVisita());
		visita.setGestorResponsavel(gestor);
		visita.setPromotor(promotor);
		visita.setNomeLocalVisita(novaVisita.getNomeLocalVisita());
		visita.setLocalizacaoLatitude(novaVisita.getLocalizacaoLatitude());
		visita.setLocalizacaoLongitude(novaVisita.getLocalizacaoLongitude());
		
		return repository.save(visita);
	}
	
	public String deletarVisita(Long id){	
		if(!repository.findById(id).isPresent()) {
			throw new RegraDeNegocioException("Visita não existe");
		}
		
		repository.delete(repository.findById(id).get());		
		return "objeto deletado com sucesso";
	}

	public Visita iniciarVisita(Long idVisita) {
		
		if(!repository.findById(idVisita).isPresent()) {
			throw new RegraDeNegocioException("Visita que você deseja iniciar não foi encontrada ou não existe");
		}
		
		Visita visita = repository.findById(idVisita).get();
		
		if (visita.getDataEHoraInicioVista()!=null) {
			throw new RegraDeNegocioException("Visita já foi iniciada na data: " + visita.getDataEHoraInicioVista());
		}
		
		visita.setDataEHoraInicioVista(LocalDateTime.now());
		repository.save(visita);
		return visita;
	}
	
	public Visita finalizarVisita(Long idVisita) {

		if (!repository.findById(idVisita).isPresent()) {
			throw new RegraDeNegocioException("Visita que você deseja finalizar não foi encontrada ou não existe");
		}

		Visita visita = repository.findById(idVisita).get();
		
		if (visita.getDataEHoraInicioVista()==null) {
			throw new RegraDeNegocioException("Esta visita não foi iniciada, portanto não pode ser finalizada");
		}
		
		if (visita.getDataEHoraFimVista()!=null) {
			throw new RegraDeNegocioException("Visita já foi finalizada na data: " + visita.getDataEHoraFimVista());
		}
		
		visita.setDataEHoraFimVista(LocalDateTime.now());
		repository.save(visita);
		return visita;
	}
	
	public Visita registrarAtividade(Long idVisita, String descricaoAtividade, ArrayList<String> imagensVisitaPath) {

		if (!repository.findById(idVisita).isPresent()) {
			throw new RegraDeNegocioException("Visita ou não existe");
		}

		Visita visita = repository.findById(idVisita).get();
	
		visita.setAtividadesRealizadas(descricaoAtividade);
		visita.addImagenVisita(imagensVisitaPath);
		repository.save(visita);
		return visita;
	}
	
	
	public Visita registrarEstoque(Long idVisita, String estoqueRepresentado) throws JsonMappingException, JsonProcessingException {

		if (!repository.findById(idVisita).isPresent()) {
			throw new RegraDeNegocioException("Visita ou não existe");
		}
		
		HashMap<String, String> map = (HashMap<String, String>) Arrays.asList(estoqueRepresentado.split(",")).
				stream().map(s -> s.split(":")).collect(Collectors.toMap(e -> e[0], e -> e[1]));
		
		Visita visita = repository.findById(idVisita).get();		
		visita.setEstoqueRepresentado(map);
		repository.save(visita);
		return visita;
	}
	
	public Visita registrarPrecosConcorrencia(Long idVisita, String precosConcorrencia) throws JsonMappingException, JsonProcessingException {

		if (!repository.findById(idVisita).isPresent()) {
			throw new RegraDeNegocioException("Visita ou não existe");
		}
		
		HashMap<String, String> map = (HashMap<String, String>) Arrays.asList(precosConcorrencia.split(",")).
				stream().map(s -> s.split(":")).collect(Collectors.toMap(e -> e[0], e -> e[1]));
		
		Visita visita = repository.findById(idVisita).get();		
		visita.setPrecosConcorrencia(map);
		repository.save(visita);
		return visita;
	}
	
}
