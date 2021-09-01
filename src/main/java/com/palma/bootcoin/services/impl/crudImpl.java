package com.palma.bootcoin.services.impl;

import com.palma.bootcoin.services.crud;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class crudImpl<T,ID> implements crud<T,ID> {
	protected abstract ReactiveMongoRepository<T, ID> getRepo();
	
	@Override
	public Mono<T> create(T t){
		return getRepo().save(t);
	}
	
	@Override
	public Mono<T> update(T t){
		return getRepo().save(t);
	}
	
	@Override
	public Flux<T> findAll(){
		return getRepo().findAll();
	}
	
	@Override
	public Mono<T> findById(ID id){
		return getRepo().findById(id);
	}
	
	@Override
	public Mono<Void> deleteById(ID id){
		return getRepo().deleteById(id);
	}
}
