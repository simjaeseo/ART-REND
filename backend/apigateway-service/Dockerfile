FROM openjdk:17-ea-11-jdk-slim AS build-stage

WORKDIR /var/jenkins_home/workspace/deploy/backend/apigateway-service
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJAR

FROM openjdk:17-ea-11-jdk-slim
COPY --from=build-stage build/libs/apigateway-service-0.1.jar ApigatewayService.jar
ENTRYPOINT ["java","-jar","ApigatewayService.jar"]