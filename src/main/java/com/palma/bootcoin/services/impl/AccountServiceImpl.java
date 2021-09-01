package com.palma.bootcoin.services.impl;

import java.util.UUID;

import com.palma.bootcoin.models.Account;
import com.palma.bootcoin.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;

import com.palma.bootcoin.services.AccountService;

import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl extends crudImpl<Account, UUID> implements AccountService {
	
	@Autowired
	private AccountRepository repo;
	
	@Override
	protected ReactiveMongoRepository<Account, UUID> getRepo() {
		return repo;
	}
	
	@Override
	public Mono<Account> findByCellPhoneNumber(String cellphoneNumber){
		return repo.findFirstByCellphoneNumber(cellphoneNumber);
	}
	
}
