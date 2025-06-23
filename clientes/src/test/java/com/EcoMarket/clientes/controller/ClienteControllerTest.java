package com.EcoMarket.clientes.controller;

import com.EcoMarket.clientes.Model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("controller_cliente@test.com"))
                .andExpect(jsonPath("$.nombre").value("Test Controller Cliente"));

        mockMvc.perform(get("/clientes/controller_cliente@test.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Test Controller Cliente"))
                .andExpect(jsonPath("$.telefono").value("555666777"));
    }
}
