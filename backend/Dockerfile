FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package

FROM openjdk:21-slim
WORKDIR /app

COPY --from=build /app/target/quarkus-app/lib/ ./lib
COPY --from=build /app/target/quarkus-app/*.jar ./
COPY --from=build /app/target/quarkus-app/app/ ./app
COPY --from=build /app/target/quarkus-app/quarkus/ ./quarkus

EXPOSE 8080

ENV QUARKUS_REDIS_HOSTS=redis://redis:6379

CMD ["java", "-jar", "quarkus-run.jar"]