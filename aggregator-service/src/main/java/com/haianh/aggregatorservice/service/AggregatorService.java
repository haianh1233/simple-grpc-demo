package com.haianh.aggregatorservice.service;

import com.haianh.calculator.CalculatorServiceGrpc;
import com.haianh.calculator.Input;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class AggregatorService {

    @GrpcClient("calculator-service")
    private CalculatorServiceGrpc.CalculatorServiceBlockingStub blockingStub;

    public List<Long> getDoubles(final int number) {
        Input input = Input.newBuilder()
                .setNumber(number)
                .build();

        List<Long> result = new ArrayList<>();
        blockingStub.getAllDoubles(input)
                .forEachRemaining(t -> result.add(t.getResult()));
        return result;
    }
}
