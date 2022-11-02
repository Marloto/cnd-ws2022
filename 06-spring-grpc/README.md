### Compile

Die Datenobjekte und Stubs sollten sich mit dem `mvn compile` ergeben

### Service

Es gibt einen Service-Stub MyServiceImpl und eine innere Klasse MyServiceImplBase

### Test

- `grpcurl --plaintext localhost:9898 list`
- `grpcurl --plaintext localhost:9898 list de.thi.inf.sesa.grpc.MyService`
- `grpcurl --plaintext -d '{"name": "test"}' localhost:9898 de.thi.inf.sesa.grpc.MyService.SayHello`