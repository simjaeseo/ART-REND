FROM openjdk:17-ea-11-jdk-slim
WORKDIR /var/jenkins_home/workspace/deploy/backend/discovery-service
CMD ["./gradlew", "clean", "build"]
ARG JAR_FILE_PATH=build/libs/discovery-service-0.1.jar
COPY ${JAR_FILE_PATH} DiscoveryService.jar
ENTRYPOINT ["java","-jar","DiscoveryService.jar"]