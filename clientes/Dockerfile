# Stage 1: Build con Maven y JDK 17
FROM maven:3.9.0-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos el pom.xml para cachear dependencias
COPY pom.xml .

RUN mvn dependency:go-offline

# Copiamos el código fuente
COPY src ./src

# Build sin tests para acelerar
RUN mvn clean package -DskipTests

# Stage 2: Imagen base para correr el jar
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiamos el jar desde el build stage
COPY --from=build /app/target/clientes-service-0.0.1-SNAPSHOT.jar app.jar

# Exponemos puerto 8080
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
