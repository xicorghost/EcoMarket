# Microservicio de Productos - EcoMarket

Este microservicio permite gestionar productos usando Spring Boot con persistencia en SQLite.

---

## Requisitos

- Java 17 o superior
- Maven 3.8 o superior
- (Opcional) Docker Desktop

---

## Levantar el microservicio

### Sin Docker

1. Compila el proyecto:
   ```bash
   mvn clean package

Ejecuta el microservicio:
java -jar target/productos-service-0.0.1-SNAPSHOT.jar
El servicio estará disponible en:
http://localhost:8080/productos

Con Docker
Construye la imagen:
docker build -t ecomarket-productos:latest .

Ejecuta el contenedor:
docker run -d -p 8080:8080 --name productos-service ecomarket-productos:latest

Endpoints
GET /productos: Obtener todos los productos

GET /productos/{codigo}: Obtener producto por código

POST /productos: Crear nuevo producto

PUT /productos/{codigo}: Actualizar producto existente

DELETE /productos/{codigo}: Eliminar producto

Probar con Postman

Ejemplo para crear producto:
POST http://localhost:8080/productos
Content-Type: application/json

{
  "codigo": "P001",
  "nombre": "Zapatos deportivos",
  "precio": 49.99,
  "stock": 20
}
Notas
La base de datos SQLite se guarda localmente en productos.db.

El microservicio levanta automáticamente la base de datos con JPA.

Puedes probar los endpoints GET directamente en el navegador.

Los datos persisten mientras no borres el archivo productos.db.