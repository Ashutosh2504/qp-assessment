# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file from your host machine into the container
COPY build/libs/grocery-0.0.1-SNAPSHOT.jar /app/grocery-0.0.1-SNAPSHOT.jar

# Expose port 8080 (default port for Spring Boot applications)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/grocery-0.0.1-SNAPSHOT.jar"]
