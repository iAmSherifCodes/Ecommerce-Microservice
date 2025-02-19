FROM openjdk:21-jdk

WORKDIR /app

COPY target/Service-Registry-0.0.1-SNAPSHOT.jar service-registry.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "service-registry.jar"]
