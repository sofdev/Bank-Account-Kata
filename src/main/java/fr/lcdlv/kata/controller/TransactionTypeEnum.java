package fr.lcdlv.kata.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionTypeEnum {
	DEPOSIT("Deposit"), WITHDRAW("Withdraw");

	private String type;

}
