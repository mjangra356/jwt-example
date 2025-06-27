package com.example.jwtauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/admin")
    public String adminHello() {
        return "Hello Admin!";
    }

    @GetMapping("/user")
    public String userHello() {
        return "Hello User!";
    }
    @GetMapping("")
    public String health() {
        return "UP";
    }
}

