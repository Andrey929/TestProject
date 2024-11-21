FROM openjdk:17-jdk-slim
MAINTAINER baeldung.com
LABEL authors="Andrey Yarmolyuk"

COPY ./MyTestProject-0.0.1-SNAPSHOT.war /app.war

ENTRYPOINT ["java","-jar","/app.war"]