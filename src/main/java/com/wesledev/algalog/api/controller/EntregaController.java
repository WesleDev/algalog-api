package com.wesledev.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wesledev.algalog.api.assembler.EntregaMapper;
import com.wesledev.algalog.api.model.DestinatarioModel;
import com.wesledev.algalog.api.model.EntregaModel;
import com.wesledev.algalog.api.model.input.EntregaInput;
import com.wesledev.algalog.domain.model.Entrega;
import com.wesledev.algalog.domain.model.service.SolicitacaoEntregaService;
import com.wesledev.algalog.domain.repository.EntregaRepository;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	@Autowired
	private SolicitacaoEntregaService solicitacaoEntregaService;
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@Autowired
	private EntregaMapper entregaMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = entregaMapper.toEntity(entregaInput);
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
		return entregaMapper.toModel(entregaSolicitada);
	}
	
	@GetMapping
	public List<EntregaModel> listar() {
		return entregaMapper.toCollectionModel(entregaRepository.findAll());
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(entregaMapper.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());		
				
//					EntregaModel entregaModel = new EntregaModel();
//					entregaModel.setId(entrega.getId());
//					entregaModel.setNomeCliente(entrega.getCliente().getNome());
//					entregaModel.setDestinatario(new DestinatarioModel());
//					entregaModel.getDestinatario().setNome(entrega.getDestinatario().getNome());
//					entregaModel.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
//					entregaModel.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
//					entregaModel.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
//					entregaModel.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
//					entregaModel.setTaxa(entrega.getTaxa());
//					entregaModel.setStatus(entrega.getStatus());
//					entregaModel.setDataPedido(entrega.getDataPedido());
//					entrega.setDataFinalizacao(entrega.getDataFinalizacao());
					
//					return ResponseEntity.ok(entregaModel);
//				})
//				.orElse(ResponseEntity.notFound().build());
	}
}
