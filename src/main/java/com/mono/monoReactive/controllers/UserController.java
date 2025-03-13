package com.mono.monoReactive.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mono.monoReactive.model.User;
import com.mono.monoReactive.services.UserService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get user by ID
    @GetMapping("/{id}")
    public Mono<User> getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    // Update user details
    @PutMapping("/{id}")
    public Mono<User> updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // Register User (Signup)
    @PostMapping("/signup")
    public Mono<ResponseEntity<String>> registerUser(@RequestBody User user) {
        return userService.registerUser(user)
                .map(token -> ResponseEntity.ok("Bearer " + token));
    }

    // User Login
    @PostMapping("/login")
    public Mono<ResponseEntity<String>> loginUser(@RequestBody User user) {
        return userService.loginUser(user)
                .map(token -> ResponseEntity.ok("Bearer " + token));
    }
}
