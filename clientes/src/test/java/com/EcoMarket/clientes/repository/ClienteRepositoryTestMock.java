package com.EcoMarket.clientes.repository;

import com.EcoMarket.clientes.Model.Cliente;
import com.EcoMarket.clientes.repository.ClienteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ClienteRepositoryTestMock {

    @Mock
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        cliente = new Cliente();
        cliente.setNombre("Test Repo Cliente");
        cliente.setEmail("repo_cliente@test.com");
        cliente.setTelefono("111222333");
    }

    @Test
    public void testGuardarYBuscarClienteMock() {
        // Simulamos que guarda bien
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        // Simulamos que encuentra el cliente por email
        when(clienteRepository.findByEmail("repo_cliente@test.com")).thenReturn(Optional.of(cliente));

        Cliente guardado = clienteRepository.save(cliente);
        Optional<Cliente> encontrado = clienteRepository.findByEmail("repo_cliente@test.com");

        assertThat(guardado).isNotNull();
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNombre()).isEqualTo("Test Repo Cliente");
        assertThat(encontrado.get().getTelefono()).isEqualTo("111222333");

        // Verificamos que se llamó a los métodos esperados
        verify(clienteRepository).save(cliente);
        verify(clienteRepository).findByEmail("repo_cliente@test.com");
    }
}
