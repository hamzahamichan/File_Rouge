# Étape 1 : Utiliser l'image Ubuntu la plus récente
FROM ubuntu:latest

# Étape 2 : Ajouter une étiquette pour l'auteur
LABEL authors="user"

# Étape 3 : Installer Java sur l'image Ubuntu
RUN apt-get update && apt-get install -y openjdk-21-jdk

# Étape 4 : Définir une variable d'environnement pour Java (facultatif, mais utile)
ENV JAVA_HOME /usr/lib/jvm/java-21-openjdk-amd64
ENV PATH $JAVA_HOME/bin:$PATH

# Étape 5 : Créer un répertoire pour l'application
WORKDIR /app

# Étape 6 : Copier le fichier JAR généré dans le conteneur
COPY target/fileRouge-0.0.1-SNAPSHOT.jar /app/app.jar

# Étape 7 : Exposer le port sur lequel l'application Spring Boot sera disponible
EXPOSE 9090

# Étape 8 : Définir l'ENTRYPOINT pour exécuter l'application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
