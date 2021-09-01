package com.palma.bootcoin.services.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import com.palma.bootcoin.dto.MovementDTO;
import com.palma.bootcoin.repository.MovementRepository;
import com.palma.bootcoin.services.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;

import com.palma.bootcoin.models.Movement;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovementServiceImpl extends crudImpl<Movement, UUID> implements MovementService {
	
	@Autowired
	private MovementRepository repo;
	
	@Override
	protected ReactiveMongoRepository<Movement, UUID> getRepo() {
		return repo;
	}
	@Override
	public Mono<Movement> publishOffer(Movement body){
		body.setId(UUID.randomUUID());
		body.setDateMovement(LocalDateTime.now());
		body.setTypeMovement("Purchase");
		body.setState("P");
		
		return repo.save(body);
	}
	
	@Override
	public Flux<Movement> findAllOffers(){
		return repo.findAllByState("P");
	}
	
	@Override
	public Mono<Movement> sell(UUID id, MovementDTO body){
		
		return Mono.empty();
	}
}
