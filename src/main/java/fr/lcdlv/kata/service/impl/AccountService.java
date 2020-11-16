package fr.lcdlv.kata.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.lcdlv.kata.domain.Account;
import fr.lcdlv.kata.repository.AccountRepository;
import fr.lcdlv.kata.service.IAccountService;

@Service
public class AccountService implements IAccountService{
	
	@Autowired
	private AccountRepository accountRepository;
	
	public Optional<Account> getAccount(final int accountNumber) {
		return accountRepository.getAccountByAccountNumber(accountNumber);
	}

}
