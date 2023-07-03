FROM openjdk:17-jdk
WORKDIR /opt
ADD target/api-*.jar api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/api.jar"]