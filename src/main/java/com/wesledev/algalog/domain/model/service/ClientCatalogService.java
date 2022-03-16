package com.wesledev.algalog.domain.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wesledev.algalog.domain.model.Client;
import com.wesledev.algalog.domain.model.exception.BusinessException;
import com.wesledev.algalog.domain.repository.ClientRepository;

@Service
public class ClientCatalogService {

	@Autowired
	private ClientRepository clientRepository;

	public Client search(Long clientId) {
		return  clientRepository.findById(clientId)
				.orElseThrow(() -> new BusinessException("Cliente não encontrado"));
	}
	
	
	@Transactional
	public Client save(Client client) {
		boolean emailEmUso = clientRepository.findByEmail(client.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(client));
		
		if(emailEmUso) {
			throw new BusinessException("Já existe um cliente cadastrado com este e-mail.");
		}
		
		return clientRepository.save(client);
	}
	
	@Transactional
	public void delete(Long clientId) {
		clientRepository.deleteById(clientId);
	}
}
