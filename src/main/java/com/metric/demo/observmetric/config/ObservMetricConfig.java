package com.metric.demo.observmetric.config;

import com.metric.demo.observmetric.service.NameService;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObservMetricConfig {


    @Bean
    public LoggingMeterRegistry loggingMeterRegistry() {
        LoggingMeterRegistry loggingMeterRegistry =  new LoggingMeterRegistry();
        loggingMeterRegistry.config()
                .meterFilter(MeterFilter.denyNameStartsWith("jvm"))
                .meterFilter(MeterFilter.denyNameStartsWith("disk"));
        return loggingMeterRegistry;
    }

    @Bean
    public NameService nameService(LoggingMeterRegistry loggingMeterRegistry){
         return new NameService(loggingMeterRegistry);
    }
}
