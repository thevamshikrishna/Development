package com.atyeti.Experiments.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AnotherAppCaller {
    private final RestTemplate restTemplate;

    public AnotherAppCaller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/call-app2")
    public String callApp2() {
        String url = "http://localhost:8081/api/data";
        return restTemplate.getForObject(url, String.class);
    }
}
