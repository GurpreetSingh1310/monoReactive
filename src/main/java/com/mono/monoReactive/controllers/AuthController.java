package com.mono.monoReactive.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mono.monoReactive.model.User;
import com.mono.monoReactive.services.UserService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Register user and return JWT
    @PostMapping("/signup")
    public Mono<ResponseEntity<String>> registerUser(@RequestBody User user) {
        return userService.registerUser(user)
                .map(token -> ResponseEntity.ok("Bearer " + token));
    }

    // Login and return JWT
    @PostMapping("/login")
    public Mono<ResponseEntity<String>> loginUser(@RequestBody User user) {
        return userService.loginUser(user)
                .map(token -> ResponseEntity.ok("Bearer " + token));
    }
}