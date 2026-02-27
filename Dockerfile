# Use official maven image to build and run the tests
FROM maven:3.9.4-eclipse-temurin-17 AS builder

# Set the working directory
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Default command to run the tests
CMD ["mvn", "clean", "test"]
