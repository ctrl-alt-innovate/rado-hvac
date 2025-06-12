# Use official OpenJDK 21 runtime base image (Alpine for smaller size)
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy your built jar into the container
COPY build/libs/server-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
