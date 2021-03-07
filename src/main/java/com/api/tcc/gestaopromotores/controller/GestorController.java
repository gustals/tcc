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

import com.api.tcc.gestaopromotores.entities.Gestor;
import com.api.tcc.gestaopromotores.entitiesDTO.GestorDTO;
import com.api.tcc.gestaopromotores.service.GestorService;

import io.swagger.annotations.ApiOperation;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(value = "/gestor")
public class GestorController {
	
	
	@Autowired
	private GestorService service;
	
	
	@GetMapping
	public ResponseEntity<List<Gestor>> buscarTodos(){
		List<Gestor> gestores = service.findAll();
		return ResponseEntity.ok(gestores);
	}
	
	@ApiOperation(value = "Retorna os dados de um gestor específico")
	@GetMapping(value = "/{idGestor}")
	public ResponseEntity buscarPorId(@PathVariable(value="idGestor") Long id){
		try {
			Gestor objGestor = service.findById(id);
			return ResponseEntity.ok(objGestor);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Cria um novo gestor")
	@PostMapping
	public ResponseEntity criarGestor(@RequestBody GestorDTO gestorNovo ){
		try {
			Gestor gestor = service.criarGestor(gestorNovo);
			return ResponseEntity.ok(gestor);
		}catch(Exception e){		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Atualizar os dados de um gestor especifico")
	@PutMapping(value = "/{idGestor}")
	public ResponseEntity alterarGestor(@PathVariable(value="idGestor") Long id, @RequestBody GestorDTO gestorNovo ){
		try {
			Gestor gestor = service.alterarGestor(id, gestorNovo);
			return ResponseEntity.ok(gestor);
		}catch(Exception e){		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Deleta o cadastro de um gestor")
	@DeleteMapping(value = "/{idGestor}")
	public ResponseEntity deletarGestor(@PathVariable(value="idGestor") Long id ){
		try {
			String delete = service.deletarGestor(id);
			return ResponseEntity.ok(delete);
		}catch(Exception e){		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
