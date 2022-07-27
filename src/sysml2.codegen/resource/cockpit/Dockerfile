FROM openjdk:11-jre-slim
VOLUME /tmp
COPY target/«system.name.toLow»-cockpit-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENV rabbit_host 'localhost'
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.rabbitmq.host=${rabbit_host}"]
