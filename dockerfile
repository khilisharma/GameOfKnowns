# Dockerfile

FROM maven:3.6-jdk-8
RUN mvn clean package

FROM tomcat:9.0.37-jdk8
MAINTAINER Khili<khilisharma1@gmail.com>
RUN apt-get update && apt-get -y upgrade
WORKDIR /usr/local/tomcat
COPY tomcat/tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY tomcat/context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml
COPY target/gok-api-server-1.0.0.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
