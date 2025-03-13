package com.mono.monoReactive.services;

import org.springframework.stereotype.Service;

import com.mono.monoReactive.model.User;
import com.mono.monoReactive.repository.UserRepository;

import reactor.core.publisher.Mono;

//Declaring CRUD operations for User entity

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create User
    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }

    // Get User
    public Mono<User> getUser(String id) {
        return userRepository.findById(id);
    }

    // Delete User
    public Mono<Void> deleteUser(String id) {
        return userRepository.deleteById(id);
    }

    // Update User
    public Mono<User> updateUser(String id, User user) {
        return userRepository.findById(id).flatMap(existingUser -> {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            return userRepository.save(existingUser);
        });
    }

    public Mono<User> loginUser(User user) {
        return userRepository.findByEmail(user.getEmail()).flatMap(existingUser -> {
            if (existingUser.getPassword().equals(user.getPassword())) {
                return Mono.just(existingUser);
            } else {
                return Mono.empty();
            }
        });
    }

    public Mono<String> registerUser(User user) {
        // Implementation for registering the user and returning a JWT token
        return Mono.just("dummyToken"); // Replace with actual implementation
    }
}
