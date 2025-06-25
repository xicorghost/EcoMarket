# Microservicio de Clientes - EcoMarket

Este microservicio permite gestionar clientes en memoria (sin base de datos) usando Spring Boot.

---

## Requisitos

- Java 17 o superior
- Maven 3.8 o superior

---

## Levantar el microservicio

1. Compila el proyecto:
   mvn clean package

2. Ejecuta el microservicio:
   java -jar target/clientes-service-0.0.1-SNAPSHOT.jar

El servicio estará disponible en:  
http://localhost:8088

---

## Endpoints para pruebas (Postman o navegador)

### Clientes

- Obtener todos los clientes  
  GET http://localhost:8088/clientes

- Obtener cliente por email  
  GET http://localhost:8088/clientes/{email}

- Crear cliente  
  POST http://localhost:8088/clientes  
  Content-Type: application/json

Ejemplos para crear clientes (POST):

{
  "nombre": "Juan Pérez",
  "email": "juan.perez@example.com",
  "telefono": "123456789"
}

{
  "nombre": "Ana Gómez",
  "email": "ana.gomez@example.com",
  "telefono": "987654321"
}

{
  "nombre": "Luis Martínez",
  "email": "luis.martinez@example.com",
  "telefono": "555123456"
}

{
  "nombre": "María López",
  "email": "maria.lopez@example.com",
  "telefono": "444555666"
}

- Actualizar cliente  
  PUT http://localhost:8088/clientes/{email}  
  Content-Type: application/json

- Eliminar cliente  
  DELETE http://localhost:8088/clientes/{email}

---

# Microservicio de Productos - EcoMarket

Este microservicio permite gestionar productos con base de datos (JPA + SQLite) usando Spring Boot.

---

## Requisitos

- Java 17 o superior
- Maven 3.8 o superior

---

## Levantar el microservicio

1. Compila el proyecto:
   mvn clean package

2. Ejecuta el microservicio:
   java -jar target/productos-service-0.0.1-SNAPSHOT.jar

El servicio estará disponible en:  
http://localhost:8081

---

## Endpoints para pruebas (Postman o navegador)

### Productos

- Obtener todos los productos  
  GET http://localhost:8081/productos

- Obtener producto por código  
  GET http://localhost:8081/productos/{codigo}

- Crear producto  
  POST http://localhost:8081/productos  
  Content-Type: application/json

Ejemplos para crear productos (POST):

{
  "codigo": "P001",
  "nombre": "Pan Integral",
  "precio": 1500.0
}

{
  "codigo": "P002",
  "nombre": "Leche Entera",
  "precio": 1200.0
}

{
  "codigo": "P003",
  "nombre": "Queso Chanco",
  "precio": 4500.0
}

{
  "codigo": "P004",
  "nombre": "Manzana Roja",
  "precio": 800.0
}

- Actualizar producto  
  PUT http://localhost:8081/productos/{codigo}  
  Content-Type: application/json

- Eliminar producto  
  DELETE http://localhost:8081/productos/{codigo}

---

## Notas generales

- Puedes crear varios clientes y productos usando los ejemplos JSON que se proporcionan arriba.
- Los datos en clientes se almacenan solo en memoria, por lo que se pierden al reiniciar el microservicio.
- Los productos usan base de datos (SQLite), por lo que los datos persisten mientras la base no se borre.
- Para detener cualquier microservicio:  
  - Si lo ejecutaste en terminal, presiona Ctrl + C.

---

## Cómo probar los endpoints en Postman

1. Crear una nueva petición.
2. Elegir el método HTTP (GET, POST, PUT, DELETE).
3. Escribir la URL correspondiente.
4. Para POST y PUT, ir a la pestaña Body, seleccionar raw y JSON, y pegar uno de los ejemplos JSON.
5. Hacer clic en Send y revisar la respuesta.

---

Fin de las instrucciones.
