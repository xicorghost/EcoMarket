package com.ecomarket.productos.integration;

import com.ecomarket.productos.model.Producto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")  // Usa application-test.properties para no afectar producción
public class ProductoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Producto producto;

    @BeforeEach
    public void setup() {
        producto = new Producto();
        producto.setCodigo("PRD900");
        producto.setNombre("Zapato Integracion");
        producto.setPrecio(12345);
    }

    @Test
    public void testCrearYObtenerProducto() throws Exception {
        // Crear producto via POST
        mockMvc.perform(post("/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value("PRD900"))
                .andExpect(jsonPath("$.nombre").value("Zapato Integracion"));

        // Obtener producto via GET
        mockMvc.perform(get("/productos/PRD900"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Zapato Integracion"))
                .andExpect(jsonPath("$.precio").value(12345));
    }

    @Test
    public void testActualizarProducto() throws Exception {
        producto.setNombre("Zapato Actualizado");
        producto.setPrecio(54321);

        // Actualizar via PUT
        mockMvc.perform(put("/productos/PRD900")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Zapato Actualizado"));

        // Validar que el cambio persiste
        mockMvc.perform(get("/productos/PRD900"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Zapato Actualizado"))
                .andExpect(jsonPath("$.precio").value(54321));
    }

    @Test
    public void testEliminarProducto() throws Exception {
        // Eliminar via DELETE
        mockMvc.perform(delete("/productos/PRD900"))
                .andExpect(status().isNoContent());

        // Validar que ya no existe
        mockMvc.perform(get("/productos/PRD900"))
                .andExpect(status().isNotFound());
    }
}
