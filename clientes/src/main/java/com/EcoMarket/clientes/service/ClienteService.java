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
        try {
            return clienteRepository.save(cliente);
        } catch (IllegalArgumentException e) {
            throw e;  // La excepción la maneja el controller
        }
    }

    public boolean update(int id, Cliente cliente) {
        try {
            return clienteRepository.update(id, cliente);
        } catch (IllegalArgumentException e) {
            throw e;  // La excepción la maneja el controller
        }
    }

    public boolean deleteById(int id) {
        return clienteRepository.deleteById(id);
    }
}
