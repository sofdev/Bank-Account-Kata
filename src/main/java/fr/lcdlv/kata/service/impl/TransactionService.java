package fr.lcdlv.kata.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.lcdlv.kata.controller.TransactionRequest;
import fr.lcdlv.kata.domain.Account;
import fr.lcdlv.kata.domain.Transaction;
import fr.lcdlv.kata.repository.AccountRepository;
import fr.lcdlv.kata.repository.TransactionRepository;
import fr.lcdlv.kata.service.ITransactionService;

@Service
public class TransactionService implements ITransactionService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Transactional
	public Transaction updateAccount(TransactionRequest newTransaction, Account account) {

		accountRepository.save(account);
		Transaction transaction = new Transaction();
		transaction.setTransactionType(newTransaction.getTransactionType());
		transaction.setTransactionValue(newTransaction.getAmount());
		transaction.setTransactionDate(new Date());
		transaction.setAccount(account);
		return transactionRepository.save(transaction);

	}

	public Optional<List<Transaction>> getAccountTransactionsHistory(Account account) {

		return transactionRepository.getTransactionByaccount(account);
	}

}
