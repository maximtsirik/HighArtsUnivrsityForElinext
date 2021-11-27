FROM openjdk:17
ARG JAR_FILE=target/high-arts-university.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]