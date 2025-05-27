package com.EcoMarket.clientes.repository;

import com.EcoMarket.clientes.Model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository {
    private final List<Cliente> clientes = new ArrayList<>();

    public List<Cliente> findAll() {
        return new ArrayList<>(clientes);
    }

    public Optional<Cliente> findByEmail(String email) {
        return clientes.stream().filter(c -> c.getEmail().equals(email)).findFirst();
    }

    public Cliente save(Cliente cliente) {
        clientes.add(cliente);
        return cliente;
    }

    public boolean update(String email, Cliente cliente) {
        Optional<Cliente> existing = findByEmail(email);
        if (existing.isPresent()) {
            Cliente c = existing.get();
            c.setNombre(cliente.getNombre());
            c.setTelefono(cliente.getTelefono());
            c.setEmail(cliente.getEmail());
            return true;
        }
        return false;
    }

    public boolean deleteByEmail(String email) {
        return clientes.removeIf(c -> c.getEmail().equals(email));
    }
}