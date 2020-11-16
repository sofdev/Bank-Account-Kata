package fr.lcdlv.kata.exceptions;

import lombok.Getter;

@Getter
public class AccountNotFoundException extends Exception {

	
	public AccountNotFoundException(String accountNumber) {
		super(String.format("Account number %s has not been found.", accountNumber));
	}

}
