package com.EcoMarket.clientes.service;

import com.EcoMarket.clientes.Model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test") // Config MySQL real
@Transactional
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @Test
    public void testGuardarYBuscarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Test Service Cliente");
        cliente.setEmail("service_cliente@test.com");
        cliente.setTelefono("999888777");

        Cliente guardado = clienteService.save(cliente);

        Optional<Cliente> encontrado = clienteService.findByEmail("service_cliente@test.com");

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNombre()).isEqualTo("Test Service Cliente");
    }
}
