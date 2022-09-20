FROM openjdk:17-ea-11-jdk-slim
WORKDIR /var/jenkins_home/workspace/deploy/backend/business-service
COPY gradlew .
COPY gradle gradleCOPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJAR

FROM openjdk:17-ea-11-jdk-slim
COPY --from=build-stage build/libs/business-service-0.1.jar BusinessService.jar
ENTRYPOINT ["java","-jar","BusinessService.jar"]