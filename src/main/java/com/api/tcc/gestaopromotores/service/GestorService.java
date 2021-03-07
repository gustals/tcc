package com.api.tcc.gestaopromotores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.tcc.gestaopromotores.entities.Gestor;
import com.api.tcc.gestaopromotores.entitiesDTO.GestorDTO;
import com.api.tcc.gestaopromotores.exception.RegraDeNegocioException;
import com.api.tcc.gestaopromotores.repository.GestorRepository;

@Service
public class GestorService {
	
	@Autowired
	private GestorRepository repository;
	
	public List<Gestor> findAll(){
		
		if(repository.findAll().isEmpty()) {
			throw new RegraDeNegocioException("Não existem gestores cadastrados");
		}
		return repository.findAll();
	}
	
	public Gestor findById(Long id){
		
		if(!repository.findById(id).isPresent()) {
			throw new RegraDeNegocioException("Gestor não existe");
		}
		
		return repository.findById(id).get();
	}
	
	public Gestor criarGestor(GestorDTO novoGestor) {
		if(repository.findByMatricula(novoGestor.getMatricula())!=null) {
			throw new RegraDeNegocioException("Número da matricula já esta em uso no sistema");
		}
		
		Gestor gestor = new Gestor(novoGestor.getNome(), novoGestor.getMatricula());
		return repository.save(gestor);
	}
	
	public Gestor alterarGestor(Long id, GestorDTO gestorAtualizado) {
		if(!repository.findById(id).isPresent()) {
			throw new RegraDeNegocioException("Gestor não existe");
		}
		
		Gestor gestor = repository.findById(id).get();
		gestor.setName(gestorAtualizado.getNome());
		gestor.setMatricula(gestorAtualizado.getMatricula());
		return repository.save(gestor);
	}
	
	public String deletarGestor(Long id) {
		if(!repository.findById(id).isPresent()) {
			throw new RegraDeNegocioException("Gestor não existe");
		}
		
		repository.delete(repository.findById(id).get());
		return "Gestor excluído com sucesso";
	}
}
