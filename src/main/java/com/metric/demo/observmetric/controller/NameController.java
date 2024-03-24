package com.metric.demo.observmetric.controller;

import com.metric.demo.observmetric.service.NameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
@Slf4j
public class NameController {

    public NameService nameService;

    @GetMapping(value = "/api/name/{name}")
    public String sayHello(@PathVariable String name) {
       log.info("received call for name = " + name);
       return nameService.sayHello(name);
    }

    @GetMapping(value = "/api/names")
    public Flux<String> retrieveAllNames() {
        return nameService.retrieveNames();
    }
}
