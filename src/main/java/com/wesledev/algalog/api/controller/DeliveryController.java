package com.wesledev.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wesledev.algalog.api.assembler.DeliveryMapper;
import com.wesledev.algalog.api.dto.DeliveryDTO;
import com.wesledev.algalog.api.model.input.InputDelivery;
import com.wesledev.algalog.domain.model.Delivery;
import com.wesledev.algalog.domain.model.service.FinishedDeliveryService;
import com.wesledev.algalog.domain.model.service.RequestDeliveryService;
import com.wesledev.algalog.domain.repository.DeliveryRepository;

@RestController
@RequestMapping("/entregas")
public class DeliveryController {

	@Autowired
	private RequestDeliveryService requestDeliveryService;

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private DeliveryMapper deliveryMapper;

	@Autowired
	private FinishedDeliveryService finishedDeliveryService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DeliveryDTO request(@Valid @RequestBody InputDelivery inputDelivery) {
		Delivery newDelivery = deliveryMapper.toEntity(inputDelivery);
		Delivery requestDelivery = requestDeliveryService.solicitar(newDelivery);
		return deliveryMapper.toModel(requestDelivery);
	}

	@GetMapping
	public List<DeliveryDTO> listDeliveries() {
		return deliveryMapper.toCollectionModel(deliveryRepository.findAll());
	}

	@GetMapping("/{deliveryId}")
	public ResponseEntity<DeliveryDTO> search(@PathVariable Long deliveryId) {
		return deliveryRepository.findById(deliveryId).map(delivery -> ResponseEntity.ok(deliveryMapper.toModel(delivery)))
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

	@PutMapping("/{deliveryId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finished(@PathVariable Long deliveryId) {
		finishedDeliveryService.finish(deliveryId);
	}
}
