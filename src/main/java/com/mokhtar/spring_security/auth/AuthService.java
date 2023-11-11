package com.mokhtar.spring_security.auth;


import com.mokhtar.spring_security.Config.JwtService;
import com.mokhtar.spring_security.Entity.Role;
import com.mokhtar.spring_security.Entity.User;
import com.mokhtar.spring_security.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder passwordEncoder ;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository repo, BCryptPasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterReq req) {
        var user = User.builder()
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .email(req.getUserName())
                .password(passwordEncoder.encode(req.getPassword()))
                .role(Role.USER)
                .build();
        repo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse auth(AuthRequest req) {
        System.out.println(passwordEncoder.encode(req.getPassword()));
        System.out.println(req.getPassword());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(req.getEmail(),req.getPassword());
        authenticationManager.authenticate(authenticationToken);
        var user = repo.findByEmail(req.getEmail()).orElseThrow();
        System.out.println(user.getPassword() + " email "+user.getEmail() );
        var jwtToken  = jwtService.generateToken(user);
        return AuthRes.builder().token(jwtToken).build();
    }
}