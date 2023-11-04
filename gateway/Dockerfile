FROM openjdk:11-jre as build
COPY .. /
FROM eclipse-temurin:11-jre
ARG MODULE_NAME=gateway
COPY --from=build ${MODULE_NAME}/build/libs/*.jar  app.jar
ENTRYPOINT ["java","-jar","/app.jar"]