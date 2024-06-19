# Use OpenJDK 17 on Alpine Linux for ARM64 architecture
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from your local machine to the container at /app
COPY target/studentmanagement-backend-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your application runs on (if needed)
EXPOSE 8080

# Command to run your application when the container starts
CMD ["java", "-jar", "app.jar"]