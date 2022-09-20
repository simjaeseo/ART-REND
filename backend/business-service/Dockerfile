FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
CMD ["./gradlew", "clean", "build"]
ARG JAR_FILE_PATH=build/libs/business-service-0.1.jar
COPY ${JAR_FILE_PATH} BusinessService.jar
ENTRYPOINT ["java","-jar","BusinessService.jar"]