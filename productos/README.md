Cómo levantar el microservicio

Opción 1: Ejecutar directamente (JAR)
# Ir a la carpeta del microservicio
cd src/main/java/com/EcoMarket/productos

# Compilar el proyecto
./mvnw clean package

# Ejecutar el JAR (ajustar nombre si es necesario)
java -jar target/productos-0.0.1-SNAPSHOT.jar

Por defecto quedará disponible en:
http://localhost:8080/productos


Opción 2: Levantar con Docker
Dockerfile ya incluido. Para construir y ejecutar:

# Construir imagen
docker build -t ecomarket-productos:latest .

# Ejecutar contenedor
docker run -d -p 8080:8080 --name productos-service ecomarket-productos:latest


Endpoints del API REST
Método	    Endpoint	        Descripción
GET	        /productos	        Obtener todos los productos
GET	        /productos/{id}	    Obtener producto por ID
POST	    /productos	        Crear nuevo producto
PUT	        /productos/{id}	    Actualizar producto
DELETE	    /productos/{id}	    Eliminar producto


 Pruebas con Postman o cURL

  Crear producto (POST)
URL: http://localhost:8080/productos
Método: POST
Body (JSON):
    {
  "id": 1,
  "nombre": "Café Orgánico",
  "descripcion": "Bolsa de 500g de café orgánico",
  "precio": 3990,
  "stock": 100
}

cURL:
    curl -X POST http://localhost:8080/productos \
-H "Content-Type: application/json" \
-d '{
  "id": 1,
  "nombre": "Café Orgánico",
  "descripcion": "Bolsa de 500g de café orgánico",
  "precio": 3990,
  "stock": 100
}'

Obtener todos los productos (GET)
URL: http://localhost:8080/productos
Método: GET

cURL:curl http://localhost:8080/productos


 Obtener producto por ID (GET)
URL: http://localhost:8080/productos/1
Método: GET

cURL:curl http://localhost:8080/productos/1


 Actualizar producto (PUT)
URL: http://localhost:8080/productos/1
Método: PUT
Body (JSON):
    {
  "id": 1,
  "nombre": "Café Premium",
  "descripcion": "500g café premium",
  "precio": 4590,
  "stock": 80
}

cURL:
    curl -X PUT http://localhost:8080/productos/1 \
-H "Content-Type: application/json" \
-d '{
  "id": 1,
  "nombre": "Café Premium",
  "descripcion": "500g café premium",
  "precio": 4590,
  "stock": 80
}'


Eliminar producto (DELETE)
URL: http://localhost:8080/productos/1
Método: DELETE

cURL:curl -X DELETE http://localhost:8080/productos/1
