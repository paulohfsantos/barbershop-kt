FROM gradle:8.2.1-jdk11-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test --no-daemon

FROM openjdk:11-jre-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/barbershop.jar
RUN bash -c 'touch /app/barbershop.jar'
ENTRYPOINT ["java", "-jar", "/app/barbershop.jar"]