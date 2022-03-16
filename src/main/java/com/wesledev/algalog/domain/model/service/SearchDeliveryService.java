package com.wesledev.algalog.domain.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wesledev.algalog.api.exceptionhandler.EntityNotFoundException;
import com.wesledev.algalog.domain.model.Delivery;
import com.wesledev.algalog.domain.repository.DeliveryRepository;

@Service
public class SearchDeliveryService {
	
	@Autowired
	private DeliveryRepository deliveryRepository;

	public Delivery search (Long deliveryId) {
		return deliveryRepository.findById(deliveryId)
				.orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada!"));
	}
}
