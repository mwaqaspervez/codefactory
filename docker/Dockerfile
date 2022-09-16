FROM maven:3.5.4-jdk-11 AS build

ADD target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTIONS -jar app.jar" ]