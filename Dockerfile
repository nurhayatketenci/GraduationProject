FROM openjdk:8

EXPOSE 8080

ADD target/*.jar springboot-docker.jar

ENTRYPOINT ["java", "-jar", "/springboot-docker.jar"]