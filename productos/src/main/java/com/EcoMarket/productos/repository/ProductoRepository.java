package com.ecomarket.productos.repository;

import com.ecomarket.productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // JpaRepository ya trae todos los métodos CRUD necesarios
}
