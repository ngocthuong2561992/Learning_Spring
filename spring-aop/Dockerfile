FROM openjdk:17
MAINTAINER Jack
ARG APP_NAME="spring-aop"
#COPY build/libs/${APP_NAME}-0.0.1-SNAPSHOT.jar ${APP_NAME}-0.0.1.jar
COPY build/libs/spring-aop-0.0.1-SNAPSHOT.jar spring-aop-0.0.1.jar
ENTRYPOINT ["java", "-jar", "spring-aop-0.0.1.jar"]
