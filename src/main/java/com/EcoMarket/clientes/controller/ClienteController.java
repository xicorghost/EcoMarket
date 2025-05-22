package com.EcoMarket.clientes.controller;

import com.EcoMarket.clientes.Model.Cliente;
import com.EcoMarket.clientes.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.findAll();
    }

    @GetMapping("/{email}")
    public ResponseEntity<Cliente> getClienteByEmail(@PathVariable String email) {
        return clienteService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable String email, @RequestBody Cliente cliente) {
        boolean updated = clienteService.update(email, cliente);
        if (updated) {
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCliente(@PathVariable String email) {
        boolean deleted = clienteService.deleteByEmail(email);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}