FROM openjdk:21-jdk

WORKDIR /app

COPY target/api-gateway-0.0.1-SNAPSHOT.jar apigw.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "apigw.jar"]
