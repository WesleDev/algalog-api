package com.wesledev.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wesledev.algalog.api.assembler.OccurrenceMapper;
import com.wesledev.algalog.api.dto.OcurrenceDTO;
import com.wesledev.algalog.api.model.input.InputOccurrence;
import com.wesledev.algalog.domain.model.Delivery;
import com.wesledev.algalog.domain.model.Occurrence;
import com.wesledev.algalog.domain.model.service.SearchDeliveryService;
import com.wesledev.algalog.domain.model.service.OccurrenceRegisterService;

@RestController
@RequestMapping("/entregas/{deliveryId}/ocorrencias")
public class OccurrenceController {
	
	@Autowired
	private OccurrenceRegisterService occurenceRegisterService;
	
	@Autowired
	private OccurrenceMapper occurrenceMapper;
	
	@Autowired
	private SearchDeliveryService searchDeliveryService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcurrenceDTO register(@PathVariable Long deliveryId, @Valid @RequestBody InputOccurrence inputOccurrence) {
		Occurrence occurrenceRegistered = occurenceRegisterService.register(deliveryId, inputOccurrence.getDescricao());
		
		return occurrenceMapper.toModel(occurrenceRegistered);
	}
	
	@GetMapping
	public List<OcurrenceDTO> listOccurrence(@PathVariable Long deliveryId) {
		Delivery delivery = searchDeliveryService.search(deliveryId);
		
		return occurrenceMapper.toCollectionModel(delivery.getOcorrencias());
	}
}
