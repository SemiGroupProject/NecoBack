FROM openjdk:11

VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/project-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} neconico-springboot.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","/neconico-springboot.jar"]