# define base docker image
FROM openjdk:17
LABEL maintainer="javaguides.net"
ADD target/challenge-0.0.1-SNAPSHOT.jar challenge.jar
ENTRYPOINT ["java", "-jar", "challenge.jar"]