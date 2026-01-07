# =========================
# Stage 1: Build
# =========================
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom files first (better cache)
COPY pom.xml .
COPY banking-domain/pom.xml banking-domain/pom.xml
COPY banking-application/pom.xml banking-application/pom.xml
COPY banking-infrastructure/pom.xml banking-infrastructure/pom.xml
COPY banking-api/pom.xml banking-api/pom.xml
COPY banking-bootstrap/pom.xml banking-bootstrap/pom.xml

# Download dependencies
RUN mvn -B -q dependency:go-offline

# Copy source code
COPY . .

# Build only bootstrap (brings dependencies)
RUN mvn -B -q clean package -DskipTests

# =========================
# Stage 2: Runtime
# =========================
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Security: non-root user
RUN useradd -ms /bin/bash appuser
USER appuser

# Copy jar from build stage
COPY --from=build /app/banking-bootstrap/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
