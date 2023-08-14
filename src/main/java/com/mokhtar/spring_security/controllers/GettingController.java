package com.mokhtar.spring_security.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/Web")
public class GettingController {

    @GetMapping ("/sayHello")
    public ResponseEntity<String> SayHello(){
        return ResponseEntity.ok("Hello API is working");
    }

    @GetMapping("/saygoodBy")
    public ResponseEntity<String> SaygoodBy(){
        return ResponseEntity.ok("GoodBy API is working");
    }
}
