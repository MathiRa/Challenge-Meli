FROM openjdk:11-jre-slim

ENV TZ America/Montevideo

COPY ./target/*.jar /opt/app.jar

CMD ["java", "-jar", "/opt/app.jar", "--spring.config.location=file:/deployments/config/application.yml"]