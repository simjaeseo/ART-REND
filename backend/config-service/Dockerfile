FROM openjdk:17-ea-11-jdk-slim
WORKDIR /var/jenkins_home/workspace/deploy/backend/config-service
COPY gradlew .
COPY gradle gradleCOPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJAR

FROM openjdk:17-ea-11-jdk-slim
COPY --from=build-stage build/libs/config-service-0.1.jar ConfigServer.jar
ENTRYPOINT ["java","-jar","ConfigServer.jar"]