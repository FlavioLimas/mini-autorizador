package com.atorizepoc.miniautorizador.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerProductController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/products")
    public ResponseEntity<Object> getMessage() {
        return ResponseEntity.ok("Hello from Docker!");
    }

}
