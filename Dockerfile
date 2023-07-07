FROM openjdk:17-jdk
WORKDIR /opt
ADD target/demo-0.0.1-SNAPSHOT.jar api-app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/opt/api-app.jar"]
