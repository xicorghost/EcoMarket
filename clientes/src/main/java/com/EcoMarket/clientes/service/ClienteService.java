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
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un cliente con ese email");
        }
        return clienteRepository.save(cliente);
    }

    public boolean update(String email, Cliente cliente) {
        Optional<Cliente> existingCliente = clienteRepository.findByEmail(email);
        if (existingCliente.isPresent()) {
            Cliente c = existingCliente.get();

            if (!email.equals(cliente.getEmail()) &&
                clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Ya existe un cliente con ese email");
            }

            c.setNombre(cliente.getNombre());
            c.setEmail(cliente.getEmail());
            c.setTelefono(cliente.getTelefono());
            clienteRepository.save(c);
            return true;
        }
        return false;
    }

    public boolean deleteByEmail(String email) {
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        if (cliente.isPresent()) {
            clienteRepository.delete(cliente.get());
            return true;
        }
        return false;
    }
}
