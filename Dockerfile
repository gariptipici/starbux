FROM maven:3.6.3-jdk-11-slim AS build
WORKDIR usr/src/app
COPY . ./
RUN mvn clean package

FROM openjdk:8-jdk-alpine
ARG JAR_NAME="starbux-0.0.1"
WORKDIR /usr/src/app
EXPOSE 8080
COPY --from=build /usr/src/app/target/${JAR_NAME}.jar ./starbux.jar
ENTRYPOINT ["java","-jar","starbux.jar"]