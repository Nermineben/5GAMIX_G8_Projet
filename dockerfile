FROM openjdk:8-jdk-alpine
EXPOSE 9090
ADD ./target/KaddemProject-1.0.jar app.jar
ENTRYPOINT [ "java", "-jar", "./app.jar" ]
