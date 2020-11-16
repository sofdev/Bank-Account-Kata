package fr.lcdlv.kata.exceptions;

import fr.lcdlv.kata.controller.TransactionRequest;
import lombok.Getter;

@Getter
public class TransactionNotAllowedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionNotAllowedException(TransactionRequest req) {
		super(String.format("%s transaction not allowed for account with id %s", req.getTransactionType(),
				req.getAccountNumber()));
	}
}
