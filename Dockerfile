FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/spring-boot-tournament-1.0-SNAPSHOT.jar app.jar

RUN echo "Contenu de /app :" && ls -al /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]