FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER Lion Li

RUN mkdir -p /xinhu/server
RUN mkdir -p /xinhu/server/logs
RUN mkdir -p /xinhu/server/temp

WORKDIR /xinhu/server

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

ADD ./target/xinhu-admin.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=${SERVER_PORT}","-jar", "app.jar"]
