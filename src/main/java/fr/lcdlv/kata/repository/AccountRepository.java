package fr.lcdlv.kata.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.lcdlv.kata.domain.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
	
	public Optional<Account> getAccountByAccountNumber(Integer accountNumber);
}
