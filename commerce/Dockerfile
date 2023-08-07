FROM openjdk:11-jre
ARG JAR_FILE=*.jar
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
