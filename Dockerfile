FROM openjdk:17-jdk
WORKDIR /opt
ADD target/api-*.jar api.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/opt/api.jar"]
