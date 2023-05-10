FROM openjdk:17
ARG JAR_FILE=target/EtsyProject-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} .
CMD [ "java", "-jar",  "/EtsyProject-0.0.1-SNAPSHOT.jar"]
