package fr.lcdlv.kata.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransactionRequest {

	private final Integer accountNumber;

	private final Double amount;

	private final String transactionType;

}
