package com.palma.bootcoin.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.palma.bootcoin.models.Account;

import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, UUID>{
	Mono<Account> findFirstByCellphoneNumber(String cellphoneNumber);
}
