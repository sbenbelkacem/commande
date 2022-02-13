FROM openjdk:11.0.14-jdk-slim-bullseye
WORKDIR /opt
ADD target/commande-*.jar commande.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/commande.jar"]
