FROM openjdk:11
EXPOSE 8080
ADD target/projet2.jar projet2.jar
ENTRYPOINT ["java", "-jar", "/projet2.jar"]
