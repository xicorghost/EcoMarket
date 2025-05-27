package com.ecomarket.productos.service;

import com.ecomarket.productos.model.Producto;
import com.ecomarket.productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> obtenerTodos() {
        return repo.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    public Producto guardar(Producto producto) {
        return repo.save(producto);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
