FROM openjdk:17-ea-11-jdk-slim
WORKDIR /var/jenkins_home/workspace/deploy/backend/auth-service
COPY gradlew .
COPY gradle gradleCOPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJAR

FROM openjdk:17-ea-11-jdk-slim
COPY --from=build-stage build/libs/auth-service-0.1.jar AuthService.jar
ENTRYPOINT ["java","-jar","AuthService.jar"]