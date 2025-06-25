package com.EcoMarket.clientes.service;

import com.EcoMarket.clientes.Model.Cliente;
import com.EcoMarket.clientes.repository.ClienteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

public class ClienteServiceTestMock {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        cliente = new Cliente();
        cliente.setNombre("Test Service Cliente");
        cliente.setEmail("service_cliente@test.com");
        cliente.setTelefono("999888777");
    }

@Test
public void testGuardarYBuscarClienteMock() {
    // Secuencia de respuestas para findByEmail
    when(clienteRepository.findByEmail(cliente.getEmail()))
        .thenReturn(Optional.empty())        // No existe al guardar
        .thenReturn(Optional.of(cliente));   // Existe al buscar luego

    when(clienteRepository.save(cliente)).thenReturn(cliente);

    Cliente guardado = clienteService.save(cliente);
    Optional<Cliente> encontrado = clienteService.findByEmail(cliente.getEmail());

    assertThat(guardado).isNotNull();
    assertThat(encontrado).isPresent();
    assertThat(encontrado.get().getNombre()).isEqualTo("Test Service Cliente");

    verify(clienteRepository, times(2)).findByEmail(cliente.getEmail());
    verify(clienteRepository).save(cliente);
}

    @Test
    public void testEliminarClienteMock() {
        // Simulamos que el cliente existe
        when(clienteRepository.findByEmail(cliente.getEmail())).thenReturn(Optional.of(cliente));
        doNothing().when(clienteRepository).delete(cliente);

        boolean eliminado = clienteService.deleteByEmail(cliente.getEmail());

        assertThat(eliminado).isTrue();

        verify(clienteRepository).findByEmail(cliente.getEmail());
        verify(clienteRepository).delete(cliente);
    }
}
