FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8082
ARG APP_NAME="api-app"
ARG APP_VERSION="1.0"
ARG JAR_FILE="/build/libs/${APP_NAME}-${APP_VERSION}.jar"

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "app.jar"]
