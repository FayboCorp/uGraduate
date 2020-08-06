FROM openjdk:8u131-jdk-alpine

MAINTAINER Fabian Corpuz "fabiancorpuz@gmail.com"

WORKDIR = /usr/local/bin/

COPY target/uGraduate-0.0.1-SNAPSHOT.jar webapp.jar

EXPOSE 8080

CMD ["java", "-jar", "webapp.jar"]