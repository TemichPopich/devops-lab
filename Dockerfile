FROM maven:3.9.9-eclipse-temurin-23 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:23-jre-alpine AS production
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring \
    && chown -R spring:spring /app
USER spring:spring
COPY --from=build /app/target/devops-lab.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]