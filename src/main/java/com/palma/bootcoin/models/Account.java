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
public class Account {
	@Id
	@Builder.Default
	private UUID id = UUID.randomUUID();
//	Datos de la cuenta
	private String cellphoneNumber;
	private String email;
	private String documentType;
	private String documentNumber;
//	Cantidad de dinero con la que cuenta
	private Double balance;
	
}
