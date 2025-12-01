FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER Lion Li

RUN mkdir -p /xinhu/monitor
RUN mkdir -p /xinhu/monitor/logs

WORKDIR /xinhu/monitor

EXPOSE 9090

ADD ./target/xinhu-monitor-admin.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
