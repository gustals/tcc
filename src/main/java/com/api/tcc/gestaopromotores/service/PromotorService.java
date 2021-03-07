package com.api.tcc.gestaopromotores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.tcc.gestaopromotores.entities.Gestor;
import com.api.tcc.gestaopromotores.entities.Promotor;
import com.api.tcc.gestaopromotores.entitiesDTO.PromotorDTO;
import com.api.tcc.gestaopromotores.exception.RegraDeNegocioException;
import com.api.tcc.gestaopromotores.repository.PromotorRepository;

@Service
public class PromotorService {
	
	@Autowired
	private PromotorRepository repository;
	
	@Autowired
	private GestorService gestorService;
	
	
	public List<Promotor> findAll(){
		if(repository.findAll().isEmpty()) {
			throw new RegraDeNegocioException("Não existem promotores cadastrados");
		}
		return repository.findAll();
	}
	
	public Promotor findById(Long id){
		
		if(!repository.findById(id).isPresent()) {
			throw new RegraDeNegocioException("Promotor não existe");
		}
		
		return repository.findById(id).get();
	}
	
	public Promotor criarPromotor(PromotorDTO novoPromotor) {
		if(repository.findByMatricula(novoPromotor.getMatricula())!=null) {
			throw new RegraDeNegocioException("Número da matricula já esta em uso no sistema");
		}
		Gestor gestor= gestorService.findById(novoPromotor.getGestorId());
		
		Promotor promotor = new Promotor(novoPromotor.getNome(), novoPromotor.getMatricula(), gestor);
		return repository.save(promotor);
	}
	
	public Promotor alterarPromotor(Long id, PromotorDTO promotorAtualizado) {
		if(!repository.findById(id).isPresent()) {
			throw new RegraDeNegocioException("Promotor não existe");
		}
		
		Gestor gestor= gestorService.findById(promotorAtualizado.getGestorId());
		
		Promotor promotor = repository.findById(id).get();
		promotor.setNome(promotorAtualizado.getNome());
		promotor.setMatricula(promotorAtualizado.getMatricula());
		promotor.setGestor(gestor);
		return repository.save(promotor);
	}
	
	public String deletarPromotor(Long id) {
		if(!repository.findById(id).isPresent()) {
			throw new RegraDeNegocioException("Promotor não existe");
		}
		
		repository.delete(repository.findById(id).get());
		return "Promotor excluído com sucesso";
	}
	
}
