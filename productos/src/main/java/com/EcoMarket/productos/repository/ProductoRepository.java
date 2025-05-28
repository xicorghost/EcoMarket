package com.ecomarket.productos.repository;

import com.ecomarket.productos.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository {
    private final List<Producto> productos = new ArrayList<>();

    public List<Producto> findAll() {
        return new ArrayList<>(productos);
    }

    public Optional<Producto> findByCodigo(String codigo) {
        return productos.stream().filter(p -> p.getCodigo().equals(codigo)).findFirst();
    }

    public Producto save(Producto producto) {
        productos.add(producto);
        return producto;
    }

    public boolean update(String codigo, Producto producto) {
        Optional<Producto> existing = findByCodigo(codigo);
        if (existing.isPresent()) {
            Producto p = existing.get();
            p.setNombre(producto.getNombre());
            p.setPrecio(producto.getPrecio());
            p.setCodigo(producto.getCodigo());
            return true;
        }
        return false;
    }

    public boolean deleteByCodigo(String codigo) {
        return productos.removeIf(p -> p.getCodigo().equals(codigo));
    }
}
