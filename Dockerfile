#base-image
FROM openjdk:11
# Copy에서 사용될 경로 변수
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]