package com.mono.monoReactive.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mono.monoReactive.utils.JwtUtil;

import reactor.core.publisher.Mono;

@Service
public class AuthService {
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private static final String DEMO_USERNAME = "user";
    private static final String DEMO_PASSWORD_HASH = new BCryptPasswordEncoder().encode("password");

    public AuthService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Mono<String> authenticate(String username, String password) {
        if (DEMO_USERNAME.equals(username) && passwordEncoder.matches(password, DEMO_PASSWORD_HASH)) {
            return Mono.just(jwtUtil.generateToken(username));
        }
        return Mono.error(new RuntimeException("Invalid credentials"));
    }

    public Mono<Boolean> validateUser(String username, String password) {
        // Implement your user validation logic here
        return Mono.just(true); // Replace with actual validation logic
    }
    
}




