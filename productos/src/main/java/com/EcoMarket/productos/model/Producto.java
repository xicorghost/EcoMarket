package com.ecomarket.productos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Producto {

    @Id
    private String codigo;  // Esto indica que 'codigo' es la clave primaria

    private String nombre;
    private double precio;

    public Producto() {
        // Constructor vacío obligatorio para JPA
    }

    public Producto(String nombre, String codigo, double precio) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
    }

    // getters y setters (igual que antes)

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                ", precio=" + precio +
                '}';
    }
}
