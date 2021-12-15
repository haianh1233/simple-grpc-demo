package com.haianh.calculatorservice.service;

import com.haianh.calculator.CalculatorServiceGrpc;
import com.haianh.calculator.Input;
import com.haianh.calculator.Output;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.stream.IntStream;

@GrpcService
public class CalculatorService extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public void getAllDoubles(Input request, StreamObserver<Output> response) {
        int number = request.getNumber();
        IntStream.rangeClosed(1, number)
                .map(t -> t * 2)
                .mapToObj(t -> Output.newBuilder().setResult(t).build())
                .forEach(response::onNext);
        response.onCompleted();
    }
}
