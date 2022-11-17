FROM maven:3-amazoncorretto-17 AS builder
WORKDIR /tmp/
COPY pom.xml /tmp/
RUN mvn dependency:go-offline -B
COPY src /tmp/src/
RUN mvn package

FROM amazoncorretto:17-alpine3.13 AS app

WORKDIR /app
COPY --from=builder /tmp/target/hexa*.jar /app/app.jar
COPY --from=builder /tmp/target/lib/ /app/lib
ENTRYPOINT ["java","-jar","app.jar"]