# Microservicio de Clientes - EcoMarket

Este microservicio permite gestionar clientes en memoria (sin base de datos) usando Spring Boot.

---

## Requisitos

- Java 17 o superior
- Maven 3.8 o superior

*(Opcional para Docker: Docker Desktop)*

---

## Levantar el microservicio

### Sin Docker

1. **Compila el proyecto:**
   ```sh
   mvn clean package
   ```

2. **Ejecuta el microservicio:**
   ```sh
   java -jar target/clientes-service-0.0.1-SNAPSHOT.jar
   ```
   *(Verifica el nombre del JAR en la carpeta `target/`)*

3. **El servicio estará disponible en:**  
   [http://localhost:8080](http://localhost:8080)

---

### Con Docker

1. **Asegúrate de tener Docker Desktop instalado y corriendo.**

2. **Levanta el servicio:**
   ```sh
   docker compose up --build
   ```
   o
   ```sh
   docker-compose up --build
   ```

---

## Endpoints para pruebas (Postman o navegador)

- **Obtener todos los clientes**
  ```
  GET http://localhost:8080/clientes
  ```

- **Obtener cliente por email**
  ```
  GET http://localhost:8080/clientes/{email}
  ```

- **Crear cliente**
  ```
  POST http://localhost:8080/clientes
  Content-Type: application/json

  {
    "nombre": "Juan",
    "email": "juan@correo.com",
    "telefono": "123456789"
  }
  ```

- **Actualizar cliente**
  ```
  PUT http://localhost:8080/clientes/{email}
  Content-Type: application/json

  {
    "nombre": "Juan Actualizado",
    "email": "juan@correo.com",
    "telefono": "987654321"
  }
  ```

- **Eliminar cliente**
  ```
  DELETE http://localhost:8080/clientes/{email}
  ```

---

---

## Cómo probar los endpoints en Postman

### 1. Obtener todos los clientes

- **Método:** GET  
- **URL:** `http://localhost:8080/clientes`
- **Pasos en Postman:**
  1. Abre Postman y crea una nueva petición.
  2. Selecciona el método `GET`.
  3. Escribe la URL.
  4. Haz clic en **Send**.

---

### 2. Obtener cliente por email

- **Método:** GET  
- **URL:** `http://localhost:8080/clientes/{email}`
- **Pasos en Postman:**
  1. Crea una nueva petición.
  2. Selecciona el método `GET`.
  3. Escribe la URL, reemplazando `{email}` por el email del cliente (por ejemplo: `http://localhost:8080/clientes/juan@correo.com`).
  4. Haz clic en **Send**.

---

### 3. Crear cliente

- **Método:** POST  
- **URL:** `http://localhost:8080/clientes`
- **Body:** Selecciona `raw` y `JSON` e ingresa uno de estos ejemplos:
  ```json
  {
    "nombre": "Juan",
    "email": "juan@correo.com",
    "telefono": "123456789"
  }
  ```
  ```json
  {
    "nombre": "Ana",
    "email": "ana@correo.com",
    "telefono": "555123456"
  }
  ```
- **Pasos en Postman:**
  1. Crea una nueva petición.
  2. Selecciona el método `POST`.
  3. Escribe la URL.
  4. Ve a la pestaña **Body**, selecciona **raw** y elige **JSON**.
  5. Pega el JSON de ejemplo.
  6. Haz clic en **Send**.

---

### 4. Actualizar cliente

- **Método:** PUT  
- **URL:** `http://localhost:8080/clientes/{email}`
- **Body:** Selecciona `raw` y `JSON` e ingresa, por ejemplo:
  ```json
  {
    "nombre": "Juan Actualizado",
    "email": "juan@correo.com",
    "telefono": "987654321"
  }
  ```
- **Pasos en Postman:**
  1. Crea una nueva petición.
  2. Selecciona el método `PUT`.
  3. Escribe la URL, reemplazando `{email}` por el email del cliente.
  4. Ve a la pestaña **Body**, selecciona **raw** y elige **JSON**.
  5. Pega el JSON de ejemplo.
  6. Haz clic en **Send**.

---

### 5. Eliminar cliente

- **Método:** DELETE  
- **URL:** `http://localhost:8080/clientes/{email}`
- **Pasos en Postman:**
  1. Crea una nueva petición.
  2. Selecciona el método `DELETE`.
  3. Escribe la URL, reemplazando `{email}` por el email del cliente.
  4. Haz clic en **Send**.

---

## Notas

- Puedes crear varios clientes cambiando los datos del JSON.
- Los datos se pierden al reiniciar el microservicio (almacenamiento en memoria).
- Los endpoints GET pueden probarse también en el navegador.

## Detener el microservicio

- Si lo ejecutaste en terminal, presiona `Ctrl + C`.
- Si usaste Docker, ejecuta:
  ```
  docker compose down
  ```
  o
  ```
  docker-compose down
  ```

---

## Notas

- Los datos se almacenan solo en memoria y se pierden al reiniciar el microservicio.
- Puedes probar los endpoints con Postman, Insomnia o tu navegador (solo los GET).