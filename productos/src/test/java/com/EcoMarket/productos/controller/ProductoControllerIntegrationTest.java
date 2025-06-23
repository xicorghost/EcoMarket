package com.ecomarket.productos.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import org.springframework.test.context.ActiveProfiles;

import com.ecomarket.productos.model.Producto;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ProductoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCrearYObtenerProducto() {
        Producto producto = new Producto();
        producto.setCodigo("PRD888");
        producto.setNombre("Zapato Controlador Integracion");
        producto.setPrecio(7777);

        ResponseEntity<Producto> postResponse = restTemplate.postForEntity("/productos", producto, Producto.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Producto creado = postResponse.getBody();
        assertThat(creado).isNotNull();

        ResponseEntity<Producto> getResponse = restTemplate.getForEntity("/productos/" + creado.getCodigo(), Producto.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().getNombre()).isEqualTo("Zapato Controlador Integracion");
    }

    // Puedes agregar tests para update y delete igual
}
