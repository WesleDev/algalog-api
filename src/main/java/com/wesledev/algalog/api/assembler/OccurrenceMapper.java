package com.wesledev.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wesledev.algalog.api.dto.OcurrenceDTO;
import com.wesledev.algalog.domain.model.Occurrence;

@Component
public class OccurrenceMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public OcurrenceDTO toModel(Occurrence occurrence) {
		return modelMapper.map(occurrence,OcurrenceDTO.class);
	}
	
	public List<OcurrenceDTO> toCollectionModel(List<Occurrence> occurrencies) {
		return occurrencies.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
}
