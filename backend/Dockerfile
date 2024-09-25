FROM maven:3.8.3-openjdk-17 AS build

LABEL authors="ramzi.bouzaiene"

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src/

RUN mvn clean install -DskipTests

FROM openjdk:17-alpine

COPY --from=build /app/target/workshop-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]