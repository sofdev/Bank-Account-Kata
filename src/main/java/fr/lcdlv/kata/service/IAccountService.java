package fr.lcdlv.kata.service;

import java.util.Optional;

import fr.lcdlv.kata.domain.Account;


public interface IAccountService {

	public Optional<Account> getAccount(final int accountNumber);
}
