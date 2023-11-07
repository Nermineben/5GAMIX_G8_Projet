FROM maven:3.3-jdk-11

RUN mvn dependency:get \
  -DremoteRepositories=http://192.168.1.15:8081/repository/maven-public/ \
  -DgroupId=tn.esprit.spring \
  -DartifactId=kaddem \
  -Dversion=1.0 \
  -Dtransitive=false \
  -Ddest=./kaddem-api.jar

ENTRYPOINT [ "java", "-jar", "./kaddem-api.jar" ] 
