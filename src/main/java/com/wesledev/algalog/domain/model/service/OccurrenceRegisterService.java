package com.wesledev.algalog.domain.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wesledev.algalog.domain.model.Delivery;
import com.wesledev.algalog.domain.model.Occurrence;

@Service
public class OccurrenceRegisterService {
	
	@Autowired
	private SearchDeliveryService searchDeliveryService;
	
	@Transactional
	public Occurrence register(Long deliveryId, String description) {
		Delivery delivery = searchDeliveryService.search(deliveryId);
		
		return delivery.addOccorrence(description);
	}
	
}
