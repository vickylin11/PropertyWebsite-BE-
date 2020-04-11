FROM openjdk:8
ADD target/docker-property-web.jar docker-property-web.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-property-web.jar"]