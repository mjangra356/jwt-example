package com.example.jwtauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;

@RestController
@RequestMapping("/jwt")
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
    public ResponseEntity<Resource> index() {
        Resource resource = new ClassPathResource("static/index.html");
        return ResponseEntity.ok()
            .contentType(MediaType.TEXT_HTML)
            .body(resource);
}

}

