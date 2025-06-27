# ---------- Stage 1: Build ----------
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ---------- Stage 2: Run ----------
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/jwt-auth-example-1.0.0.jar app.jar
COPY --from=builder /app/src/main/resources/static/src.zip static/src.zip
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
