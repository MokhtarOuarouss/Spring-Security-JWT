package com.mokhtar.spring_security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterReq req){
        return ResponseEntity.ok(service.register(req));
    }
    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest req){
        return ResponseEntity.ok(service.auth(req));
    }
}
