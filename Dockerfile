FROM openjdk:8-jdk-slim
ARG JAR_FILE=build/libs/clean-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom","-jar","/app.jar"]