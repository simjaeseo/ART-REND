FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
CMD ["./gradlew", "clean", "build"]
COPY build/libs/business-service-0.1.jar BusinessService.jar
ENTRYPOINT ["java","-jar","BusinessService.jar"]