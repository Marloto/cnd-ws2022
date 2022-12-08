# Eureka Example

## Usage

_Run Service_

```
cd eureka-service
mvn spring-boot:run
```

_Run Some Client_

```
cd eureka-client
mvn spring-boot:run
```

_Run Other Client_

```
cd eureka-client
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

Open [http://localhost:8761] and see, that there are service instances registered.