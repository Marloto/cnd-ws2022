# Config Server

Use configuration from git repositories. Currently uses test repo: https://github.com/Marloto/spring-boot-config-example.git

## Usage

```
cd config-server
mvn spring-boot:run

# run for test (see configurations)

curl http://localhost:8888/sesa01/development
curl http://localhost:8888/sesa01/productive

# other terminal

cd config-client
mvn spring-boot:run

# run for test

curl http://localhost:8080/hello # prints Hello, World

# close client and test again

cd config-client
mvn spring-boot:run -Dspring-boot.run.profiles=productive

# run for test

curl http://localhost:8080/hello # prints Hello, Universe
```