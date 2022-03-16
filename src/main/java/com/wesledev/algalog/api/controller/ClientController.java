package com.wesledev.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wesledev.algalog.domain.model.Client;
import com.wesledev.algalog.domain.model.service.ClientCatalogService;
import com.wesledev.algalog.domain.repository.ClientRepository;

@RestController
@RequestMapping("/clientes")
public class ClientController {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ClientCatalogService catalogClientService;

	@GetMapping
	public List<Client> listClients() {
		return clientRepository.findAll();
	}

	@GetMapping("/{clientId}")
	public ResponseEntity<Client> search(@PathVariable Long clientId) {
		return clientRepository.findById(clientId).map(client -> ResponseEntity.ok(client))
				.orElse(ResponseEntity.notFound().build());

//    	Optional<Cliente> cliente = clienteRepository.findById(clientId);
//	     if(cliente.isPresent()) {
//	    	 return ResponseEntity.ok(cliente.get());
//	     }
//	     
//	     return ResponseEntity.notFound().build();
//	    }
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client add(@Valid @RequestBody Client client) {
//    	return clienteRepository.save(cliente);
		return catalogClientService.save(client);
	}

	@PutMapping("/{clientId}")
	public ResponseEntity<Client> update(@Valid @PathVariable Long clientId, @RequestBody Client client) {
		if (!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}

		client.setId(clientId);
//    	cliente = clienteRepository.save(cliente);
		client = catalogClientService.save(client);

		return ResponseEntity.ok(client);
	}

	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> remove(@PathVariable Long clientId) {
		if (!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}

//    	clienteRepository.deleteById(clientId);
		catalogClientService.delete(clientId);

		return ResponseEntity.noContent().build();
	}
}
