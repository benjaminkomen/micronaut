FROM maven:3.6.3-jdk-11 as builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

## Build a release artifact.
RUN mvn package -DskipTests

FROM oracle/graalvm-ce:19.3.1-java11 as graalvm
COPY --from=builder /app/target/micronaut-*.jar /app/
WORKDIR /app
RUN gu install native-image
RUN native-image --no-server --enable-http --enable-https -cp micronaut-*.jar

FROM debian:stretch
EXPOSE 8080
COPY --from=graalvm /app ./app
#RUN apt-get update && apt-get -y install strace
ENTRYPOINT ["./app/micronaut"]

#
#FROM adoptopenjdk/openjdk11:jdk-11.0.6_10-alpine-slim
#
#COPY --from=builder /app/target/micronaut-*.jar /micronaut.jar
#
## Run the web service on container startup.
#CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=${PORT}","-jar","/micronaut.jar"]
