package com.ecomarket.productos.service;

import com.ecomarket.productos.model.Producto;
import com.ecomarket.productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Optional<Producto> findByCodigo(String codigo) {
        return productoRepository.findByCodigo(codigo);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public boolean update(String codigo, Producto producto) {
        return productoRepository.update(codigo, producto);
    }

    public boolean deleteByCodigo(String codigo) {
        return productoRepository.deleteByCodigo(codigo);
    }
}
