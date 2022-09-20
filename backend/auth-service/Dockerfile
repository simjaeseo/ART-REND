FROM openjdk:17-ea-11-jdk-slim
WORKDIR /var/jenkins_home/workspace/deploy/backend/auth-service
CMD ["./gradlew", "clean", "build"]
ARG JAR_FILE_PATH=build/libs/auth-service-0.1.jar
COPY ${JAR_FILE_PATH} AuthService.jar
ENTRYPOINT ["java","-jar","AuthService.jar"]