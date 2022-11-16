FROM alpine:3.14


#RUN apk add openjdk8-jre

ENV PLACE=World

WORKDIR /stuff

RUN echo "Hello, ${PLACE}" > bar

COPY foo ./
COPY dosomething.sh ./
ADD https://en.wikipedia.org/wiki/File:Example.jpg ./

RUN chmod +x dosomething.sh && ./dosomething.sh

VOLUME [ "/data" ]

ENTRYPOINT ["cat", "foo"]
