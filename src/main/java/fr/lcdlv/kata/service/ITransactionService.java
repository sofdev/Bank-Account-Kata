package fr.lcdlv.kata.service;

import java.util.List;
import java.util.Optional;

import fr.lcdlv.kata.controller.TransactionRequest;
import fr.lcdlv.kata.domain.Account;
import fr.lcdlv.kata.domain.Transaction;
import fr.lcdlv.kata.exceptions.AccountNotFoundException;


public interface ITransactionService {
	
	public Transaction updateAccount(TransactionRequest newTransaction, Account account) throws AccountNotFoundException;

	Optional<List<Transaction>> getAccountTransactionsHistory(Account account);
}
