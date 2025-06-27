package com.example.jwtauth.controller;

import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.*;

@RestController
public class DownloadController {

    @GetMapping("/jwt")
    public ResponseEntity<Resource> downloadZip() throws IOException {
        Path zipPath = Paths.get("src/main/resources/static/src.zip");
        Resource resource = new InputStreamResource(Files.newInputStream(zipPath));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=jwt-auth-example.zip")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}

