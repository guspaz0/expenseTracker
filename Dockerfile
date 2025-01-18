FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/expenseTracker-0.0.1.jar
COPY ${JAR_FILE} app_expensetracker.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app_expensetracker.jar"]