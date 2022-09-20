FROM openjdk:17-ea-11-jdk-slim
WORKDIR /var/jenkins_home/workspace/deploy/backend/apigateway-service
CMD ["./gradlew", "clean", "build"]
ARG JAR_FILE_PATH=build/libs/apigateway-service-0.1.jar
COPY ${JAR_FILE_PATH} ApigatewayService.jar
ENTRYPOINT ["java","-jar","ApigatewayService.jar"]