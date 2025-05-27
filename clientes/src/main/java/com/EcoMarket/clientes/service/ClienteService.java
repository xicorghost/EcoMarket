package com.EcoMarket.clientes.service;

import com.EcoMarket.clientes.Model.Cliente;
import com.EcoMarket.clientes.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public boolean update(String email, Cliente cliente) {
        return clienteRepository.update(email, cliente);
    }

    public boolean deleteByEmail(String email) {
        return clienteRepository.deleteByEmail(email);
    }
}