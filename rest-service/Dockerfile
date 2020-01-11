FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY application.yml /application.yml
COPY ./wait-for-it.sh /wait-for-it.sh
COPY ${JAR_FILE} app.jar
