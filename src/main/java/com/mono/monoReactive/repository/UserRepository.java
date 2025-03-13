package com.mono.monoReactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.mono.monoReactive.model.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByEmail(String email);
}
