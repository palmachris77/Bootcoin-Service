package com.palma.bootcoin.services.impl;

import java.util.UUID;

import com.palma.bootcoin.repository.CurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;

import com.palma.bootcoin.models.CurrencyRate;

import com.palma.bootcoin.services.CurrencyRateService;

import reactor.core.publisher.Mono;
@Service
public class CurrencyRateServiceImpl extends crudImpl<CurrencyRate, UUID> implements CurrencyRateService {
	
	@Autowired
	private CurrencyRateRepository repo;
	
	@Override
	protected ReactiveMongoRepository<CurrencyRate, UUID> getRepo() {
		return repo;
	}

	@Override
	public Mono<CurrencyRate> findFirstByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency) {
		return repo.findFirstByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
	}
}
