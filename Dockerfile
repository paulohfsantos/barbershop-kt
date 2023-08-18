FROM gradle:8.2.1-jdk11 AS build
WORKDIR /app
COPY . .
RUN gradle build -x test --no-daemon
FROM openjdk:11-jre-slim
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
