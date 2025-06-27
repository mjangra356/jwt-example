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
        Resource resource;
        Path zipPath = Paths.get("src/main/resources/static/src.zip");
        if (Files.exists(zipPath)) {
            // ✅ File exists on disk (local dev)
            resource = new InputStreamResource(Files.newInputStream(zipPath));
        } else {
            // ✅ Fall back to classpath (packaged inside the JAR)
            resource = new ClassPathResource("static/src.zip");
            if (!resource.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .build();
            }
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=src.zip")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}

