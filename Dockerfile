FROM amazoncorretto:11-alpine-jdk
MAINTAINER IntelCan
COPY target/shopping-list-0.0.1-SNAPSHOT.jar shopping-list-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/shopping-list-SNAPSHOT.jar"]