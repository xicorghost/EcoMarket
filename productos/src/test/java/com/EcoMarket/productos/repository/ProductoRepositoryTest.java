package com.ecomarket.productos.repository;

import com.ecomarket.productos.model.Producto;
import com.ecomarket.productos.repository.ProductoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProductoRepositoryTest {

    @Mock
    private ProductoRepository productoRepository;

    private Producto producto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        producto = new Producto();
        producto.setCodigo("PRD001");
        producto.setNombre("Zapato Escolar");
        producto.setPrecio(19990.0);
    }

    @Test
    public void testGuardarProducto() {
        Producto productoNuevo = new Producto();
        productoNuevo.setCodigo("PRD002");
        productoNuevo.setNombre("Zapato Deportivo");
        productoNuevo.setPrecio(29990.0);

        when(productoRepository.save(productoNuevo)).thenReturn(productoNuevo);

        Producto guardado = productoRepository.save(productoNuevo);

        assertThat(guardado).isNotNull();
        assertThat(guardado.getCodigo()).isEqualTo("PRD002");
        assertThat(guardado.getNombre()).isEqualTo("Zapato Deportivo");
        assertThat(guardado.getPrecio()).isEqualTo(29990.0);

        verify(productoRepository, times(1)).save(productoNuevo);
    }

    @Test
    public void testBuscarProducto() {
        when(productoRepository.findByCodigo("PRD001")).thenReturn(Optional.of(producto));

        Optional<Producto> encontrado = productoRepository.findByCodigo("PRD001");

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getCodigo()).isEqualTo("PRD001");
        assertThat(encontrado.get().getNombre()).isEqualTo("Zapato Escolar");
        assertThat(encontrado.get().getPrecio()).isEqualTo(19990.0);

        verify(productoRepository, times(1)).findByCodigo("PRD001");
    }
}
