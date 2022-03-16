package com.wesledev.algalog.domain.model.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wesledev.algalog.domain.model.Client;
import com.wesledev.algalog.domain.model.Delivery;
import com.wesledev.algalog.domain.model.DeliveryStatus;
import com.wesledev.algalog.domain.repository.DeliveryRepository;

@Service
public class RequestDeliveryService {

	@Autowired
	private ClientCatalogService clientCatalogService;

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Transactional
	public Delivery solicitar(Delivery delivery) {
		Client client = clientCatalogService.search(delivery.getCliente().getId());

		delivery.setCliente(client);
		delivery.setStatus(DeliveryStatus.PENDENTE);
		delivery.setDataPedido(OffsetDateTime.now());

		return deliveryRepository.save(delivery);
	}

}
