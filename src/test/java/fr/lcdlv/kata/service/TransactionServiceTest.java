package fr.lcdlv.kata.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import fr.lcdlv.kata.controller.TransactionRequest;
import fr.lcdlv.kata.controller.TransactionTypeEnum;
import fr.lcdlv.kata.domain.Account;
import fr.lcdlv.kata.domain.Transaction;
import fr.lcdlv.kata.exceptions.AccountNotFoundException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration()
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TransactionServiceTest {

	@Autowired
	ITransactionService transactionService;
	private static Account account;
	private final static int accountNumber = 1111;

	@BeforeAll
	public static void init() {
		account = new Account(1, accountNumber, 1100.0);
	}

	@Test
	@DisplayName("Update Account Test")
	public void update_Account_Test() throws AccountNotFoundException {

		final Double transactionValue = 10.0;

		TransactionRequest transactionRequest = new TransactionRequest(1111, transactionValue,
				TransactionTypeEnum.DEPOSIT.getType());
		Transaction transaction = transactionService.updateAccount(transactionRequest, account);
		Assertions.assertAll(() -> Assertions.assertNotNull(transaction),
				() -> Assertions.assertEquals(transaction.getTransactionValue(), transactionValue));
	}

	@Test
	public void account_transactions_history_Test() {
		Optional<List<Transaction>> transactions = transactionService.getAccountTransactionsHistory(account);
		assertThat(transactions.isPresent());

	}
}
