package com.palma.bootcoin.services;

import java.util.UUID;

import com.palma.bootcoin.models.CurrencyRate;

import reactor.core.publisher.Mono;

public interface CurrencyRateService extends crud<CurrencyRate, UUID> {
	Mono<CurrencyRate> findFirstByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);
}
