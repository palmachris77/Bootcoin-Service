package com.palma.bootcoin.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface crud<T,ID> {
	Mono<T> create(T t);
	Mono<T> update(T t);
	Flux<T> findAll();
	Mono<T> findById(ID id);
	Mono<Void> deleteById(ID id);
}
