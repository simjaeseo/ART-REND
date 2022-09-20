FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
CMD ["./gradlew", "clean", "build"]
COPY build/libs/config-service-0.1.jar DiscoveryService.jar
ENTRYPOINT ["java","-jar","DiscoveryService.jar"]