# Post Service with Hexagonal Architecture

An example implementation of the Post service considering approaches from the Hexagonal Architecture. Ensure that there is an independent domain model. Use cases are defined and implemented for the model using outgoing ports. Ingoing ports are defined abstractly. Finally, adapters are added to realize the technical implementation of the endpoints.

## Usage

Start Mosquitto-Service with Docker: `docker-compose up` or `docker-compose up -d` as background service.

## Run Tests

Use `mvn clean test` to run tests.