FROM openjdk:8-jdk-alpine
COPY build/libs/*.jar backend.jar
ENTRYPOINT ["java", "-jar", "/backend.jar"]
