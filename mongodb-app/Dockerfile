FROM openjdk:17
VOLUME /tmp
ADD target/mongodb-app-0.0.1.jar mongodb-app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/mongodb-app.jar"]
