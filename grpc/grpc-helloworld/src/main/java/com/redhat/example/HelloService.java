package com.redhat.example;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

@GrpcService 
public class HelloService implements Greeter {  

    @Override
    public Uni<HelloReply> sayHello(HelloRequest request) { 
        return Uni.createFrom().item(() ->
                HelloReply.newBuilder().setMessage("Hello " + request.getName() + " have a great day!").build()
        );
    }
}