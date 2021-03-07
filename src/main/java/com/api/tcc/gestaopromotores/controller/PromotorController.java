package com.api.tcc.gestaopromotores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.tcc.gestaopromotores.entities.Promotor;
import com.api.tcc.gestaopromotores.entitiesDTO.PromotorDTO;
import com.api.tcc.gestaopromotores.service.PromotorService;

import io.swagger.annotations.ApiOperation;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(value = "/promotor")
public class PromotorController {
	
	@Autowired
	private PromotorService service;
	
	
	@GetMapping
	public ResponseEntity<List<Promotor>> buscarTodos(){
		List<Promotor> promotores = service.findAll();
		return ResponseEntity.ok(promotores);
	}
	
	@ApiOperation(value = "Retorna os dados de um promotor específico")
	@GetMapping(value = "/{idPromotor}")
	public ResponseEntity buscarPorId(@PathVariable(value="idPromotor") Long id){
		try {
			Promotor objPromotor = service.findById(id);
			return ResponseEntity.ok(objPromotor);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Cria um novo promotor")
	@PostMapping
	public ResponseEntity criarPromotor(@RequestBody PromotorDTO promotorNovo ){
		try {
			Promotor promotor = service.criarPromotor(promotorNovo);
			return ResponseEntity.ok(promotor);
		}catch(Exception e){		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Atualizar os dados de um promotor especifico")
	@PutMapping(value = "/{idPromotor}")
	public ResponseEntity alterarPromotor(@PathVariable(value="idPromotor") Long id, @RequestBody PromotorDTO promotorNovo ){
		try {
			Promotor promotor = service.alterarPromotor(id, promotorNovo);
			return ResponseEntity.ok(promotor);
		}catch(Exception e){		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Deleta o cadastro de um promotor")
	@DeleteMapping(value = "/{idPromotor}")
	public ResponseEntity deletarPromotor(@PathVariable(value="idPromotor") Long id ){
		try {
			String delete = service.deletarPromotor(id);
			return ResponseEntity.ok(delete);
		}catch(Exception e){		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


}
