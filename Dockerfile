# Utiliser une image Java officielle
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail dans le container
WORKDIR /app

# Copier le .jar généré par Maven dans le container
COPY target/*.jar app.jar

# Exposer le port utilisé par Spring Boot
EXPOSE 8080

# Commande de lancement
ENTRYPOINT ["java", "-jar", "app.jar"]
