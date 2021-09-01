package com.palma.bootcoin.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.palma.bootcoin.models.Movement;

import reactor.core.publisher.Flux;

@Repository
public interface MovementRepository extends ReactiveMongoRepository<Movement, UUID>{
	Flux<Movement> findAllByState(String state);
}
