package fr.lcdlv.kata.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import fr.lcdlv.kata.domain.Account;

@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceTest {

	@Mock
	IAccountService accountService;

	@Test
	@DisplayName("find account nominal case")
	void should_return_an_account() {
		// Given
		final int accountNumber = 1111;
		Account account = new Account(1, accountNumber, 1000.0);
		Optional<Account> optionalAccount = Optional.of(account);

		// When
		when(accountService.getAccount(accountNumber)).thenReturn(optionalAccount);
		Optional<Account> accountOptional = accountService.getAccount(accountNumber);

		// Then
		Assertions.assertAll(() -> Assertions.assertTrue(accountOptional.isPresent()),
				() -> Assertions.assertEquals(accountOptional.get().getAccountNumber(), accountNumber));

	}

	@Test
	@DisplayName("case account not found")
	void account_not_found_case() {

		// Given
		final int accountNumber = 1111;

		// When
		Optional<Account> accountOptional = accountService.getAccount(accountNumber);

		// Then
		Assertions.assertFalse(accountOptional.isPresent());

	}
}
