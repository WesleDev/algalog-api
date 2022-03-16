package com.wesledev.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wesledev.algalog.api.dto.DeliveryDTO;
import com.wesledev.algalog.api.model.input.InputDelivery;
import com.wesledev.algalog.domain.model.Delivery;

@Component
public class DeliveryMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public DeliveryDTO toModel(Delivery delivery) {
		return modelMapper.map(delivery, DeliveryDTO.class);
	}
	
	public List<DeliveryDTO> toCollectionModel(List<Delivery> deliveries) {
		return deliveries.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Delivery toEntity(InputDelivery deliveryInput) {
		return modelMapper.map(deliveryInput, Delivery.class);
	}
}
