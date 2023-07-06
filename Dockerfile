FROM openjdk:17-jdk
WORKDIR /opt
ADD target/api-app.jar api-app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/opt/api-app.jar"]
