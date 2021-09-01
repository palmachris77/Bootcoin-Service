package com.palma.bootcoin.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document
public class CurrencyRate {
	@Id
	@Builder.Default
	private UUID id = UUID.randomUUID();
	//Primer tipo de moneda
	private String fromCurrency;
	//Segundo tipo de moneda
	private String toCurrency;
    //Tasa de compra
	private Double rate;
}
