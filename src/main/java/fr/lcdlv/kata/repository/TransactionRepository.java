package fr.lcdlv.kata.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.lcdlv.kata.domain.Account;
import fr.lcdlv.kata.domain.Transaction;

@Repository
public interface TransactionRepository  extends CrudRepository<Transaction, Integer> {

	
	Optional<List<Transaction>> getTransactionByaccount(@Param("account") Account account);
}
