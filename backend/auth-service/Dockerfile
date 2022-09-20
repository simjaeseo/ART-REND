FROM openjdk:17-ea-11-jdk-slim

VOLUME /tmp
COPY build/libs/auth-service-0.1.jar AuthService.jar

ENTRYPOINT ["java","-jar","AuthService.jar"]