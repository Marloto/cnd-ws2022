# Gateway Configuration

Simple gateway configuration used in combination with eureka.

## Usage

_Terminal 1_

```
cd eureka-service
mvn spring-boot:run
```

_Terminal 2_

```
cd eureka-client01
mvn spring-boot:run
```

_Terminal 3_

```
cd eureka-client02
mvn spring-boot:run
```

_Terminal 4_

```
cd gateway-demo
mvn spring-boot:run
```

Test: `curl http://localhost:8080/hello` as well as `curl http://localhost:8080/something`