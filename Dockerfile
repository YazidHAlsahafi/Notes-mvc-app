# Use an official Maven image that includes OpenJDK as the base image
FROM maven:3.8.6-openjdk-17-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven build files and project source code to the container
COPY . .

# Package the application and skip tests
RUN mvn clean package -DskipTests

# Expose the port that the app will run on
EXPOSE 8081

# Run the JAR file with the specified port
ENTRYPOINT ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar", "--server.port=8081"]