FROM --platform=linux/amd64 maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM --platform=linux/amd64 openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/app.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app/app.jar"]