FROM openjdk:17-ea-11-jdk-slim
CMD ["./gradlew", "clean", "build"]
VOLUME /tmp
COPY build/libs/business-service-0.1.jar BusinessService.jar
ENTRYPOINT ["java","-jar","BusinessService.jar"]