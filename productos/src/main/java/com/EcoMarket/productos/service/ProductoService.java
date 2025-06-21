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

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorCodigo(String codigo) {
        return productoRepository.findByCodigo(codigo);
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public boolean actualizarProducto(String codigo, Producto productoActualizado) {
        return productoRepository.findById(codigo).map(p -> {
            p.setNombre(productoActualizado.getNombre());
            p.setPrecio(productoActualizado.getPrecio());
            productoRepository.save(p);
            return true;
        }).orElse(false);
    }

    public boolean eliminarPorCodigo(String codigo) {
        return productoRepository.findById(codigo).map(p -> {
            productoRepository.delete(p);
            return true;
        }).orElse(false);
    }
}
