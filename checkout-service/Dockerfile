#FROM ubuntu:latest
#LABEL authors="Ali"
#
#ENTRYPOINT ["top", "-b"]
FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} checkout-service.jar
ENTRYPOINT ["java","-jar","/checkout-service.jar"]
EXPOSE 8082