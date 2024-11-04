FROM openjdk:17-jdk-slim

EXPOSE 8080

WORKDIR /app

COPY . /app

CMD ["./mvnw", "spring-boot:run"]