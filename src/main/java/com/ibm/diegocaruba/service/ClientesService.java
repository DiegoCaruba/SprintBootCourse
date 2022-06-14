package com.ibm.diegocaruba.service;

import com.ibm.diegocaruba.model.Cliente;
import com.ibm.diegocaruba.repository.ClientesReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    private ClientesReposiroty repository;

    @Autowired
    public ClientesService(ClientesReposiroty repository) {
        this.repository = repository;
    }

    public void salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        this.repository.persistir(cliente);
    }

    public void validarCliente(Cliente cliente) {
        // TODO
    }
}
