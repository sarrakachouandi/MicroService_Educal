FROM openjdk:8
EXPOSE 8087
ADD target/Event-MS-0.0.1-SNAPSHOT.jar eventms.jar
ENTRYPOINT ["java", "-jar", "eventms.jar"]