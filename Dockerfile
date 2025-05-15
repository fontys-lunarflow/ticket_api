FROM maven:3.9.9-eclipse-temurin-21-alpine as build
WORKDIR /usr/src
COPY . /usr/src/
RUN mvn package

FROM maven:3.9.9-eclipse-temurin-21-alpine
RUN mkdir /usr/jar/
COPY --from=build /usr/src/target/*.jar /usr/jar/ticket_api.jar
CMD [ "java", "-jar", "/usr/jar/ticket_api.jar"]
LABEL org.opencontainers.image.source=https://github.com/fontys-lunarflow/ticket_api
