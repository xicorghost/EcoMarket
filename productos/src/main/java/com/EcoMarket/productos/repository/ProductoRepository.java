package com.EcoMarket.productos.repository;

import com.EcoMarket.productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // JpaRepository ya trae todos los métodos CRUD necesarios
}
