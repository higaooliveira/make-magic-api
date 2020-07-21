FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=build/libs/make-magic-docker-0.1.0.jarheroku container:release web
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]