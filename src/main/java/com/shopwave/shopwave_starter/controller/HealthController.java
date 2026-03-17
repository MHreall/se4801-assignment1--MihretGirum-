package com.shopwave.shopwave_starter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// simple controller to check if app is running
@RestController
public class HealthController {

    @GetMapping("/health")
    public String healthCheck() {
        return "{\"status\": \"UP\"}";
    }
}
