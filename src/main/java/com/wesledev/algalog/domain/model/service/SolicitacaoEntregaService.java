package com.wesledev.algalog.domain.model.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wesledev.algalog.domain.model.Cliente;
import com.wesledev.algalog.domain.model.Entrega;
import com.wesledev.algalog.domain.model.StatusEntrega;
import com.wesledev.algalog.domain.repository.EntregaRepository;

@Service
public class SolicitacaoEntregaService {
	
	@Autowired
	private CatalogoClienteService catalogoClienteService;

	@Autowired
	private EntregaRepository entregaRepository;

	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente =  catalogoClienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		
		return entregaRepository.save(entrega);
	}
	
}
