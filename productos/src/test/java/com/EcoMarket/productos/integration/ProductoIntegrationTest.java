package com.ecomarket.productos.integration;

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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
public class ProductoIntegrationTest {

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
        producto.setCodigo("PRD900");
        producto.setNombre("Zapato Integracion");
        producto.setPrecio(12345);
    }

    @Test
    public void testCrearYObtenerProducto() throws Exception {
        // Mock para guardar producto
        when(productoService.guardarProducto(any(Producto.class))).thenReturn(producto);
        // Mock para obtener producto
        when(productoService.obtenerPorCodigo("PRD900")).thenReturn(Optional.of(producto));

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
        Producto productoActualizado = new Producto();
        productoActualizado.setCodigo("PRD900");
        productoActualizado.setNombre("Zapato Actualizado");
        productoActualizado.setPrecio(54321);

        // Mock para actualizar
        when(productoService.actualizarProducto(Mockito.eq("PRD900"), any(Producto.class))).thenReturn(true);
        // Mock para obtener producto actualizado
        when(productoService.obtenerPorCodigo("PRD900")).thenReturn(Optional.of(productoActualizado));

        // Actualizar via PUT
        mockMvc.perform(put("/productos/PRD900")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productoActualizado)))
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
        // Mock para eliminar: retorna true
        when(productoService.eliminarPorCodigo("PRD900")).thenReturn(true);
        // Mock para que ya no encuentre el producto
        when(productoService.obtenerPorCodigo("PRD900")).thenReturn(Optional.empty());

        // Eliminar via DELETE
        mockMvc.perform(delete("/productos/PRD900"))
                .andExpect(status().isNoContent());

        // Validar que ya no existe
        mockMvc.perform(get("/productos/PRD900"))
                .andExpect(status().isNotFound());
        }

}
