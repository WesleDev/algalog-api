package com.wesledev.algalog.api.controller;

import com.wesledev.algalog.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Wesle");
        cliente1.setEmail("teste@teste.com");
        cliente1.setTelefone("999999999999");

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Joao");
        cliente2.setEmail("teste@teste.com.br");
        cliente2.setTelefone("999999999999");

        return Arrays.asList(cliente1, cliente2);
    }
}
