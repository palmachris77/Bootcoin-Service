package com.palma.bootcoin.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.palma.bootcoin.models.CurrencyRate;

import reactor.core.publisher.Mono;


@Repository
public interface CurrencyRateRepository extends ReactiveMongoRepository<CurrencyRate, UUID>{
	Mono<CurrencyRate> findFirstByFromCurrencyAndToCurrency(String from, String to);
}
