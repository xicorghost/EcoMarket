package com.EcoMarket.clientes.repository;

import com.EcoMarket.clientes.Model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test") // application-test.properties con MySQL real
@Transactional          // Rollback tras test para no ensuciar la base
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testGuardarYBuscarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Test Repo Cliente");
        cliente.setEmail("repo_cliente@test.com");
        cliente.setTelefono("111222333");

        Cliente guardado = clienteRepository.save(cliente);

        Optional<Cliente> encontrado = clienteRepository.findByEmail("repo_cliente@test.com");

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNombre()).isEqualTo("Test Repo Cliente");
        assertThat(encontrado.get().getTelefono()).isEqualTo("111222333");
    }
}
