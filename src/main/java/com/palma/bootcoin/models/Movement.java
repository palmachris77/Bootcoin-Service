package com.palma.bootcoin.models;

import java.time.LocalDateTime;
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
public class Movement {
	@Id
	@Builder.Default
	private UUID id = UUID.randomUUID();
//	Celular del emisor
	private String cellphoneFrom;
//	Celular del receptor
	private String cellphoneTo;
//	Tipo de movimientos
	private String typeMovement;
//	Estado de la transaccion
	private String state;
//	Monto
	private Double amount;
//	Tasa de cambio
	private Double rate;
//	Fecha del movimiento
	@Builder.Default
	private LocalDateTime dateMovement = LocalDateTime.now();
}
