FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
CMD ["./gradlew", "clean", "build"]
ARG JAR_FILE_PATH=./build/libs/discovery-service-0.1.jar
COPY ${JAR_FILE_PATH} DiscoveryService.jar
ENTRYPOINT ["java","-jar","DiscoveryService.jar"]