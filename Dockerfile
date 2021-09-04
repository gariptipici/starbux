FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/starbux-0.0.1.jar
ADD ${JAR_FILE} starbux.jar
ENTRYPOINT ["java","-jar","/starbux.jar"]