package com.palma.bootcoin.controllers;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palma.bootcoin.dto.MovementDTO;
import com.palma.bootcoin.models.Movement;
import com.palma.bootcoin.services.MovementService;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("bootcoin-movement")
public class MovementController {
	@Autowired
	private MovementService service;
//	Lista los movimientos realizados
	@GetMapping
	public Mono<ResponseEntity<Flux<Movement>>> findAll(){
		Flux<Movement> fluxBCMovement = service.findAll();
		return Mono.just(ResponseEntity
							.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(fluxBCMovement));
	}
//	Busca los movimientos relizados mediante el ID
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Movement>> findById(@PathVariable("id")UUID id){
		return service.findById(id)
					.map(c -> ResponseEntity.ok()
											.contentType(MediaType.APPLICATION_JSON)
											.body(c))
					.defaultIfEmpty(ResponseEntity.notFound().build());
	}

//	Actualiza los movimientos
	@PutMapping("/{id}")
	public Mono<ResponseEntity<Movement>> update(@PathVariable("id") UUID id, @RequestBody Movement entity){
		Mono<Movement> monoBody = Mono.just(entity);
		Mono<Movement> monoBD = service.findById(id);
		
		return monoBD
				.zipWith(monoBody, (bd, bcm) ->{
					bd.setAmount(bcm.getAmount());
					bd.setCellphoneFrom(bcm.getCellphoneFrom());
					bd.setCellphoneTo(bcm.getCellphoneTo());
					bd.setRate(bcm.getRate());
					
					return bd;
				})
				.flatMap(service::update)
				.map(bcm -> ResponseEntity.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(bcm))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
				
	}
//  Crea el registro de los movimientos
	@PostMapping
	public Mono<ResponseEntity<Movement>> create(@RequestBody Movement body, final ServerHttpRequest rq){
		body.setId(UUID.randomUUID());
		body.setDateMovement(LocalDateTime.now());
		return service.create(body)
				.map(bcm -> ResponseEntity.created(URI.create(rq.getURI().toString().concat("/").concat(bcm.getId().toString())))
						.contentType(MediaType.APPLICATION_JSON)
						.body(bcm)
							);
	}
//	Elimina el registro de un movimiento
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteById(@PathVariable("id")UUID id){
		return service.findById(id)
				.flatMap(cr -> service.deleteById(cr.getId())
							.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
				)
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	//Publica la ofertas que tienen el Estado de la transaccion en P (Compras)
	@PostMapping("/publish-offer")
	public Mono<ResponseEntity<Movement>> publishOffer(@RequestBody Movement body, final ServerHttpRequest rq){
		return service.publishOffer(body)
				.map(bcm -> ResponseEntity.created(URI.create(rq.getURI().toString().concat("/").concat(bcm.getId().toString())))
						.contentType(MediaType.APPLICATION_JSON)
						.body(bcm)
							);
	}
	//Lista los ofertas que tienen el Estado de la transaccion en Purchase  (compras)
	@GetMapping("/offers")
	public Mono<ResponseEntity<Flux<Movement>>> findAllOffers(){
		Flux<Movement> fluxBCMovement = service.findAllOffers();
		return Mono.just(ResponseEntity
							.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(fluxBCMovement));
	}
   //Realiza la venta 
	@PutMapping("/sell/{id}")
	public Mono<ResponseEntity<Movement>> sell(@PathVariable("id") UUID id, @RequestBody MovementDTO body){
		return service.sell(id, body)
				.map(bcm -> ResponseEntity.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(bcm))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
}
