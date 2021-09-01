package com.palma.bootcoin.services;

import java.util.UUID;

import com.palma.bootcoin.models.Account;

import reactor.core.publisher.Mono;

public interface AccountService extends crud<Account, UUID> {

	Mono<Account> findByCellPhoneNumber(String cellphoneNumber);

}
