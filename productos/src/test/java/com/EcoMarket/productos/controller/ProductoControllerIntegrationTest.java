package com.ecomarket.productos.controller;

import com.ecomarket.productos.controller.ProductoController;
import com.ecomarket.productos.model.Producto;
import com.ecomarket.productos.service.ProductoService;
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

@WebMvcTest(ProductoController.class)
public class ProductoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    public void setup() {
        producto = new Producto();
        producto.setCodigo("PRD888");
        producto.setNombre("Zapato Controlador Integracion");
        producto.setPrecio(7777);
    }

    @Test
    public void testCrearYObtenerProducto() throws Exception {
        when(productoService.guardarProducto(any(Producto.class))).thenReturn(producto);
        when(productoService.obtenerPorCodigo("PRD888")).thenReturn(Optional.of(producto));

        // Crear producto (POST)
        mockMvc.perform(post("/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value("PRD888"))
                .andExpect(jsonPath("$.nombre").value("Zapato Controlador Integracion"));

        // Obtener producto (GET)
        mockMvc.perform(get("/productos/PRD888"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Zapato Controlador Integracion"))
                .andExpect(jsonPath("$.precio").value(7777));
    }
}
