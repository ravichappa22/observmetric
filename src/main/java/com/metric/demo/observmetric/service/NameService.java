package com.metric.demo.observmetric.service;

import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.autoconfigure.tracing.MicrometerTracingAutoConfiguration;
import org.springframework.stereotype.Service;
import reactor.core.observability.micrometer.Micrometer;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Slf4j
@AllArgsConstructor
public class NameService {

    private LoggingMeterRegistry loggingMeterRegistry;

    @Observed
    public String sayHello(String name){
        log.info("Responding to name = " + name);
        return "Hello ".concat(name);
    }


    public Flux<String> retrieveNames() {
        log.info("retrieving all names");
        return Flux.fromIterable(Arrays.asList("ravi,raj,siv,peter".split(",")))
                 .name("retrieveNames")
                 .tap(Micrometer.metrics(loggingMeterRegistry));
    }
}
