FROM openjdk:11-jre as build

COPY gradlew .
COPY gradle gradle
# 권한 부여
RUN chmod +x ./gradlew

COPY .. /
RUN sed -i 's/\r$//' ./gradlew
# 프로젝트 빌드
RUN ./gradlew build
FROM eclipse-temurin:11-jre

ARG MODULE_NAME=order
COPY --from=build ${MODULE_NAME}/build/libs/*.jar  app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5006","-jar","/app.jar"]
