FROM openjdk:11
EXPOSE 8080
WORKDIR /projet2
ADD target/projet2.jar /projet2/
ENTRYPOINT ["java", "-jar", "projet2.jar"]
