package com.ecomarket.productos.service;

import com.ecomarket.productos.model.Producto;
import com.ecomarket.productos.service.ProductoService;

import com.ecomarket.productos.service.ProductoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")  // Usa application-test.properties para no afectar prod
public class ProductoServiceIntegrationTest {

    @Autowired
    private ProductoService productoService;

    // Producto base para pruebas
    private Producto productoPrueba;

    @BeforeEach
    public void crearProductoPrueba() {
        productoPrueba = new Producto();
        productoPrueba.setCodigo("PRD999");
        productoPrueba.setNombre("Zapato service Test Integracion");
        productoPrueba.setPrecio(12345.0);

        // Guardar o actualizar el producto antes de cada test
        Optional<Producto> existente = productoService.obtenerPorCodigo(productoPrueba.getCodigo());
        if (existente.isEmpty()) {
            productoService.guardarProducto(productoPrueba);
        }
    }

    @Test
    public void testGuardarYObtenerProducto() {
        Optional<Producto> encontrado = productoService.obtenerPorCodigo(productoPrueba.getCodigo());
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNombre()).isEqualTo(productoPrueba.getNombre());
        assertThat(encontrado.get().getPrecio()).isEqualTo(productoPrueba.getPrecio());
    }

    @Test
    public void testActualizarProducto() {
        Producto actualizado = new Producto("Zapato actualizado", productoPrueba.getCodigo(), 6000);
        boolean resultado = productoService.actualizarProducto(productoPrueba.getCodigo(), actualizado);
        assertThat(resultado).isTrue();

        Optional<Producto> encontrado = productoService.obtenerPorCodigo(productoPrueba.getCodigo());
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNombre()).isEqualTo("Zapato actualizado");
        assertThat(encontrado.get().getPrecio()).isEqualTo(6000);
    }

    @Test
    public void testEliminarProducto() {
        boolean eliminado = productoService.eliminarPorCodigo(productoPrueba.getCodigo());
        assertThat(eliminado).isTrue();

        Optional<Producto> encontrado = productoService.obtenerPorCodigo(productoPrueba.getCodigo());
        assertThat(encontrado).isEmpty();
    }
}
