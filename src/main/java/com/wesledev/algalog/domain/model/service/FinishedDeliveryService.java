package com.wesledev.algalog.domain.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wesledev.algalog.domain.model.Delivery;
import com.wesledev.algalog.domain.repository.DeliveryRepository;

@Service
public class FinishedDeliveryService {
	
	@Autowired
	private SearchDeliveryService searchDeliveryService;
	
	@Autowired
	private DeliveryRepository deliveryRepository;

	@Transactional
	public void finish(Long deliveryId) {
		Delivery delivery = searchDeliveryService.search(deliveryId);
		
		delivery.finished();
		
		deliveryRepository.save(delivery);
		
	}
	
}
