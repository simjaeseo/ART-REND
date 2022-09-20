FROM openjdk:17-ea-11-jdk-slim
CMD ["./gradlew", "clean", "build"]
VOLUME /tmp
COPY build/libs/auth-service-0.1.jar AuthService.jar
ENTRYPOINT ["java","-jar","AuthService.jar"]