FROM amazoncorretto:17-alpine as builder
RUN apk add curl
WORKDIR /opt/app
COPY src src
COPY pom.xml pom.xml
COPY mvnw mvnw
COPY .mvn .mvn
RUN ./mvnw clean package

FROM amazoncorretto:17-alpine as runtime
WORKDIR /opt/app
USER 1000
COPY --from=builder /opt/app/target/vehiclemanagement-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS "-Dspring.config.location=src/main/resources/application.yml"
EXPOSE 8080
ENTRYPOINT java -jar app.jar