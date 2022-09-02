FROM openjdk:11-jdk-slim
MAINTAINER Reengineering-agency

COPY target/monitor-sensors-1.0.0-SNAPSHOT.war monitor-sensors-1.0.0-SNAPSHOT.war
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=prod", "/monitor-sensors-1.0.0-SNAPSHOT.war"]