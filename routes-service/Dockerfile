#FROM ubuntu:latest
#LABEL authors="Ali"
#
#ENTRYPOINT ["top", "-b"]
FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} routes-service.jar
ENTRYPOINT ["java","-jar","/routes-service.jar"]
