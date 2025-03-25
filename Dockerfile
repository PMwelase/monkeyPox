# Use an official Maven image to build the application
FROM maven:3.9.5-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . .

# Build the JAR file
RUN mvn clean package -DskipTests

# Use a minimal Java runtime for the final image
FROM openjdk:21-jdk-slim

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
