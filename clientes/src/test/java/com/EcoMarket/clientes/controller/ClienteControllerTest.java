package com.EcoMarket.clientes.controller;

import com.EcoMarket.clientes.Model.Cliente;
import com.EcoMarket.clientes.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    public void setup() {
        cliente = new Cliente();
        cliente.setNombre("Test Controller Cliente");
        cliente.setEmail("controller_cliente@test.com");
        cliente.setTelefono("555666777");
    }

    @Test
    public void testCrearYObtenerCliente() throws Exception {
        // Mock para save (guardar cliente)
        when(clienteService.save(any(Cliente.class))).thenReturn(cliente);
        // Mock para findByEmail (obtener cliente por email)
        when(clienteService.findByEmail("controller_cliente@test.com")).thenReturn(Optional.of(cliente));

        // POST crear cliente
        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("controller_cliente@test.com"))
                .andExpect(jsonPath("$.nombre").value("Test Controller Cliente"));

        // GET obtener cliente
        mockMvc.perform(get("/clientes/controller_cliente@test.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Test Controller Cliente"))
                .andExpect(jsonPath("$.telefono").value("555666777"));
    }
}
