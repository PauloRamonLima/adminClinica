FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN
WORKDIR /tmp/

COPY pom.xml /tmp/
COPY src /tmp/src/

RUN mvn clean package -DskipTests -Dmaven.repo.local=.m2

FROM alpine:latest

ENV JAVA_HOME /opt/jdk
ENV PATH ${PATH}:${JAVA_HOME}/bin
ENV JAVA_PACKAGE server-jre

ENV TOMCAT_VERSION_MAJOR 8
ENV TOMCAT_VERSION_FULL  8.5.14
ENV CATALINA_HOME /opt/tomcat

# Download and install CURL
#RUN apk --no-cache add curl

# Download and install Java
RUN apk --update add openjdk8-jre &&\
    mkdir -p /opt/jdk &&\
    ln -s /usr/lib/jvm/java-1.8-openjdk/bin /opt/jdk

# Download and install Tomcat
RUN apk add --update curl &&\
  curl -LO https://archive.apache.org/dist/tomcat/tomcat-${TOMCAT_VERSION_MAJOR}/v${TOMCAT_VERSION_FULL}/bin/apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz &&\
  curl -LO https://archive.apache.org/dist/tomcat/tomcat-${TOMCAT_VERSION_MAJOR}/v${TOMCAT_VERSION_FULL}/bin/apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz.md5 &&\
  md5sum -c apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz.md5 &&\
  gunzip -c apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz | tar -xf - -C /opt &&\
  rm -f apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz.md5 &&\
  ln -s /opt/apache-tomcat-${TOMCAT_VERSION_FULL} /opt/tomcat &&\
  rm -rf /opt/tomcat/webapps/examples /opt/tomcat/webapps/docs &&\
  apk del curl &&\
  rm -rf /var/cache/apk/* &&\
  rm -f /opt/tomcat/config/tomcat-users.xsd

ENV TZ=America/Fortaleza
RUN echo "America/Fortaleza" > /etc/timezone
RUN apk --no-cache add msttcorefonts-installer fontconfig

VOLUME /tmp
ARG JAR_FILE

COPY --from=MAVEN_TOOL_CHAIN /tmp/target/*.war /opt/tomcat/webapps/

EXPOSE 8080

CMD ${CATALINA_HOME}/bin/catalina.sh run