package de.thi.inf.cnd.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.ByteBuffer;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
//
//        HelloRequest.Builder builder = HelloRequest.newBuilder();
//        builder.setName("SomeName");
//        HelloRequest build = builder.build();
//
//        byte[] bytes = build.toByteArray();
//        //HelloRequest.parseFrom(ByteBuffer.wrap(bytes));
//
//        build.getName()



        SpringApplication.run(Application.class, args);
    }
}