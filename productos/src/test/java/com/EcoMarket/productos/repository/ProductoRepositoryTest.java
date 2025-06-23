package com.ecomarket.productos.repository;

import com.ecomarket.productos.model.Producto;
import com.ecomarket.productos.repository.ProductoRepository;

import com.ecomarket.productos.repository.ProductoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test") // Usa application-test.properties para perfil test
public class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    private final String codigoTest = "PRD001";

    @BeforeEach
    public void setup() {
        // Preparar el producto para las pruebas que lo requieran
        Producto producto = new Producto();
        producto.setCodigo(codigoTest);
        producto.setNombre("Zapato Escolar");
        producto.setPrecio(19990.0);
        // Guardar o actualizar (para asegurar que exista antes de cada prueba)
        productoRepository.save(producto);
    }

    @Test
    public void testGuardarProducto() {
        Producto productoNuevo = new Producto();
        productoNuevo.setCodigo("PRD002");
        productoNuevo.setNombre("Zapato Deportivo");
        productoNuevo.setPrecio(29990.0);

        Producto guardado = productoRepository.save(productoNuevo);

        assertThat(guardado).isNotNull();
        assertThat(guardado.getCodigo()).isEqualTo("PRD002");
        assertThat(guardado.getNombre()).isEqualTo("Zapato Deportivo");
        assertThat(guardado.getPrecio()).isEqualTo(29990.0);
    }

    @Test
    public void testBuscarProducto() {
        Producto encontrado = productoRepository.findByCodigo(codigoTest).orElse(null);

        assertThat(encontrado).isNotNull();
        assertThat(encontrado.getCodigo()).isEqualTo(codigoTest);
        assertThat(encontrado.getNombre()).isEqualTo("Zapato Escolar");
        assertThat(encontrado.getPrecio()).isEqualTo(19990.0);
    }
}
