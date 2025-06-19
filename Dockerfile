# Etapa 1: Construcción del .jar con tests usando JDK 21
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /app

COPY . .

# Usa Maven directamente si no usas wrapper
RUN mvn clean package -DskipTests

# Debug: mostrar contenido de target (opcional, puedes quitar luego)
RUN ls -la /app/target

# Etapa 2: Imagen final más liviana con JRE 21
FROM eclipse-temurin:21-jre

WORKDIR /app

# Asegúrate que esta ruta coincida con la del builder
COPY --from=builder /app/target/technical-test-0.0.1-SNAPSHOT.jar technical-test.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/technical-test.jar"]
