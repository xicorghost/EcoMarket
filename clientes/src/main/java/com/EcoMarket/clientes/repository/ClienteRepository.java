package com.EcoMarket.clientes.repository;

import com.EcoMarket.clientes.Model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository {
    private final List<Cliente> clientes = new ArrayList<>();
    private int nextId = 1;  // Contador para asignar ID incremental

    public List<Cliente> findAll() {
        return new ArrayList<>(clientes);
    }

    public Optional<Cliente> findByEmail(String email) {
        return clientes.stream().filter(c -> c.getEmail().equals(email)).findFirst();
    }
    
    public Optional<Cliente> findById(int id) {
        return clientes.stream().filter(c -> c.getId() == id).findFirst();
    }

    public Cliente save(Cliente cliente) {
        // Verificar si ya existe cliente con ese email
        if (findByEmail(cliente.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un cliente con ese email");
        }
        // Asignar ID incremental
        cliente.setId(nextId++);
        clientes.add(cliente);
        return cliente;
    }

    public boolean update(int id, Cliente cliente) {
        Optional<Cliente> existing = findById(id);
        if (existing.isPresent()) {
            Cliente c = existing.get();
            // Si cambia el email, verificar que no exista otro con ese email
            if (!c.getEmail().equals(cliente.getEmail())) {
                if (findByEmail(cliente.getEmail()).isPresent()) {
                    throw new IllegalArgumentException("Ya existe un cliente con ese email");
                }
            }
            c.setNombre(cliente.getNombre());
            c.setTelefono(cliente.getTelefono());
            c.setEmail(cliente.getEmail());
            return true;
        }
        return false;
    }

    public boolean deleteById(int id) {
        return clientes.removeIf(c -> c.getId() == id);
    }
}
