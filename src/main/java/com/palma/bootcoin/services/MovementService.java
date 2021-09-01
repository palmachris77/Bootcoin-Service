package com.palma.bootcoin.services;

import java.util.UUID;

import com.palma.bootcoin.dto.MovementDTO;
import com.palma.bootcoin.models.Movement;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovementService extends crud<Movement, UUID> {
	Mono<Movement> publishOffer(Movement body);

	Flux<Movement> findAllOffers();


	Mono<Movement> sell(UUID id, MovementDTO body);
}
