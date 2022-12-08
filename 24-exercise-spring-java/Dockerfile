FROM maven:3.8-openjdk-11 AS builder
WORKDIR /tmp/
COPY pom.xml /tmp/
RUN mvn dependency:go-offline -B
COPY src /tmp/src/
RUN mvn package

FROM openjdk:11
WORKDIR /app
COPY --from=builder /tmp/target/rest*.jar /app/app.jar
COPY --from=builder /tmp/target/lib/ /app/lib
ENTRYPOINT ["java","-jar","app.jar"]