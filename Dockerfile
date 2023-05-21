# Use an existing docker image as a base
FROM openjdk:17-jdk AS builder

# Set the working directory inside the container

WORKDIR /app

# Copy the entire project directory into the container
COPY . .

# Download and cache dependencies
RUN ["./mvnw", "dependency:go-offline"]

# Copy the source code into the container
COPY src/ ./src/

# Build the application
RUN ["./mvnw" , "package", "-DskipTests"]

# Use another existing docker image as a base
FROM openjdk:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot jar file into the container from the builder image
COPY --from=builder /app/target/leadService.jar /app/leadService.jar

# Expose the port on which the Spring Boot app will listen
EXPOSE 8080

# Set the command to run the Spring Boot app when the container starts
CMD ["java", "-jar", "leadService.jar"]