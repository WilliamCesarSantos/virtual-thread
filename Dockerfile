# -------- Build Stage --------
FROM eclipse-temurin:25-jdk AS build

# Set working directory
WORKDIR /app

# Copy only Gradle wrapper and config first (for caching)
COPY gradlew .
COPY gradle/ gradle/
COPY settings.gradle .
COPY build.gradle .

# Download dependencies (caches this layer if unchanged)
RUN chmod +x ./gradlew
RUN ./gradlew dependencies --no-daemon

# Copy the rest of the code
COPY . .

# Build the project
RUN ./gradlew bootJar --no-daemon

# -------- Run Stage --------
FROM eclipse-temurin:25-jre

WORKDIR /app

# Copy fat jar from build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","/app/app.jar"]
