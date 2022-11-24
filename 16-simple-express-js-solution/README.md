# Simple Example Service

Simple container for various examples.

## Publish

```
docker login registry-1.docker.io
docker build -t express-example .
docker image tag express-example <YOU>/express-example
docker image push <YOU>/express-example
```