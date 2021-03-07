package com.api.tcc.gestaopromotores.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.tcc.gestaopromotores.entities.Visita;
import com.api.tcc.gestaopromotores.entitiesDTO.VisitaDTO;
import com.api.tcc.gestaopromotores.service.VisitaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*")
@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(value = "/visita")
public class VisitaController {
	
	@Autowired
	private VisitaService service;
	
	
	@GetMapping
	public ResponseEntity<List<Visita>> buscarTodos(){
		List<Visita> visita = service.findAll();
		return ResponseEntity.ok(visita);
	}
	
	@ApiOperation(value = "Retorna os dados de uma visita específica")
	@GetMapping(value = "/{idVisita}")
	public ResponseEntity buscarPorId(@PathVariable(value="idVisita") Long id){
		try {
			Visita objVisitas = service.findById(id);
			return ResponseEntity.ok(objVisitas);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Retorna a lista de visitas realizadas pelo promotor no mês e ano enviados por parâmetro")
	@GetMapping("/historico/promotor/{idPromotor}")
	public ResponseEntity buscarPorPromotorMesAno(@PathVariable(value="idPromotor") Long id, @ApiParam(
		    name =  "dataBusca",
		    type = "String",
		    value = "Mes e ano das visitas",
		    example = "mm-aaaa",
		    required = true) @RequestParam String dataBusca){
		try {
			List<Visita> visita = service.buscarPorPromotorMesAno(id, dataBusca);
			return ResponseEntity.ok(visita);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@ApiOperation(value = "Retorna a lista de visitas marcadas pelo gestor no mês e ano enviados por parâmetro")
	@GetMapping("/historico/gestor/{idGestor}")
	public ResponseEntity buscarPorGestorMesAno(@PathVariable(value="idGestor") Long id, @ApiParam(
		    name =  "dataBusca",
		    type = "String",
		    value = "Mes e ano das visitas",
		    example = "mm-aaaa",
		    required = true) @RequestParam String dataBusca){
		try {
			List<Visita> visita = service.buscarPorGestorMesAno(id, dataBusca);
			return ResponseEntity.ok(visita);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@ApiOperation(value = "Gestor agenda uma nova visita para um promotor")
	@PostMapping
	public ResponseEntity criarVisita(@RequestBody VisitaDTO visitaNova ){
		try {
			Visita visita = service.criarVisita(visitaNova);
			return ResponseEntity.ok(visita);
		}catch(Exception e){		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Gestor pode atualizar todos de uma visita agendada")
	@PutMapping(value = "/{idVisita}")
	public ResponseEntity alterarVisitaGestor(@PathVariable(value="idVisita") Long id, @RequestBody VisitaDTO visitaNova ){
		try {
			Visita visita = service.alterarVisitaGestor(id, visitaNova);
			return ResponseEntity.ok(visita);
		}catch(Exception e){		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Gestor deleta uma visita qualquer")
	@DeleteMapping(value = "/{idVisita}")
	public ResponseEntity deletarVisita(@PathVariable(value="idVisita") Long id ){
		try {
			String delete = service.deletarVisita(id);
			return ResponseEntity.ok(delete);
		}catch(Exception e){		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Promotor marca a data e hora do inicio da vista")
	@PostMapping(value = "/inicio/{idVisita}")
	public ResponseEntity iniciarVisita(@PathVariable(value="idVisita") Long id){
		try {
			Visita visita = service.iniciarVisita(id);
			return ResponseEntity.ok(visita);
		}catch(Exception e){		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Promotor marca a data e hora do fim da vista")
	@PostMapping(value = "/fim/{idVisita}")
	public ResponseEntity finalizarVisita(@PathVariable(value="idVisita") Long id){
		try {
			Visita visita = service.finalizarVisita(id);
			return ResponseEntity.ok(visita);
		}catch(Exception e){		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Promotor registra as atividades realizadas na visita e também pode salvar imagens")
	@PostMapping(value = "/atividade/{idVisita}")
	public ResponseEntity registrarAtividade(@PathVariable(value="idVisita") Long id, @RequestParam String descricaoAtividade,
			@RequestParam(required=false) ArrayList<String> imagensVisitaPath){
		try {
			Visita visita = service.registrarAtividade(id, descricaoAtividade, imagensVisitaPath);
			return ResponseEntity.ok(visita);
		}catch(Exception e){		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Promotor pode alterar todos os registros de uma atividade realizadas na visita e também pode alterar as imagens salvas")
	@PutMapping(value = "/atividade/{idVisita}")
	public ResponseEntity altirarAtividade(@PathVariable(value="idVisita") Long id, @RequestParam String descricaoAtividade,
			@RequestParam(required=false) ArrayList<String> imagensVisitaPath){
		try {
			Visita visita = service.registrarAtividade(id, descricaoAtividade, imagensVisitaPath);
			return ResponseEntity.ok(visita);
		}catch(Exception e){		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Promotor registra o estoque dos produtos representados na visita")
	@PostMapping(value = "/estoque/{idVisita}")
	public ResponseEntity registrarEstoque(@PathVariable(value="idVisita") Long id, @ApiParam(
		    name =  "estoqueRepresentado",
		    type = "String",
		    value = "Nome do produto - quantidade",
		    example = "item1:10,item2:20,item3:30",
		    required = true) @RequestParam String estoqueRepresentado){
		try {
			Visita visita = service.registrarEstoque(id, estoqueRepresentado);
			return ResponseEntity.ok(visita);
		}catch(Exception e){		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Promotor altera o registro de estoque dos produtos representados na visita")
	@PutMapping(value = "/estoque/{idVisita}")
	public ResponseEntity alterarEstoque(@PathVariable(value="idVisita") Long id, @ApiParam(
		    name =  "estoqueRepresentado",
		    type = "String",
		    value = "Nome do produto - quantidade",
		    example = "item1:10,item2:20,item3:30",
		    required = true) @RequestParam String estoqueRepresentado){
		try {
			Visita visita = service.registrarEstoque(id, estoqueRepresentado);
			return ResponseEntity.ok(visita);
		}catch(Exception e){		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Promotor registra o preco dos produtos da concorrencia encontrados na visita")
	@PostMapping(value = "/precosConcorrencia/{idVisita}")
	public ResponseEntity registrarPrecosConcorrencia(@PathVariable(value="idVisita") Long id, @ApiParam(
		    name =  "precosConcorrencia",
		    type = "String",
		    value = "Nome do produto - quantidade",
		    example = "item1:10,item2:20,item3:30",
		    required = true) @RequestParam String precosConcorrencia){
		try {
			Visita visita = service.registrarPrecosConcorrencia(id, precosConcorrencia);
			return ResponseEntity.ok(visita);
		}catch(Exception e){		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Promotor registra o preco dos produtos da concorrencia encontrados na visita")
	@PutMapping(value = "/precosConcorrencia/{idVisita}")
	public ResponseEntity alterarPrecosConcorrencia(@PathVariable(value="idVisita") Long id, @ApiParam(
		    name =  "precosConcorrencia",
		    type = "String",
		    value = "Nome do produto - quantidade",
		    example = "item1:10,item2:20,item3:30",
		    required = true) @RequestParam String precosConcorrencia){
		try {
			Visita visita = service.registrarPrecosConcorrencia(id, precosConcorrencia);
			return ResponseEntity.ok(visita);
		}catch(Exception e){		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
