FROM maven:3.6.0-jdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:8
COPY --from=build /home/app/target/docker-homework-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar

ENTRYPOINT java \
    -jar /usr/local/lib/app.jar \
    --rabbitmq.host=$RABBITMQ_HOST \
    --redis.host=$REDIS_HOST \
    --spring.datasource.url=jdbc:postgresql://$POSTGRES_HOST:5432/postgres \
    --spring.datasource.username=$POSTGRES_USER \
    --spring.datasource.password=$POSTGRES_PASS \