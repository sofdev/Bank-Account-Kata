package fr.lcdlv.kata.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.lcdlv.kata.domain.Account;
import fr.lcdlv.kata.domain.Transaction;
import fr.lcdlv.kata.exceptions.AccountNotFoundException;
import fr.lcdlv.kata.exceptions.TransactionNotAllowedException;
import fr.lcdlv.kata.service.IAccountService;
import fr.lcdlv.kata.service.ITransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/bank/v1")
public class AccountController {

	@Autowired
	ITransactionService transactionService;

	@Autowired
	IAccountService accountService;

	@PostMapping(path = "/transaction", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Execute operation (Deposit or withdraw) ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Transaction Executed"),
			@ApiResponse(code = 405, message = "Not Allowed"), @ApiResponse(code = 404, message = "Not Found") })
	public ResponseEntity<Transaction> executeTransaction(
			@ApiParam(name = "transaction", value = "Transaction to execute", required = true) @RequestBody TransactionRequest transactionRequest)
			throws AccountNotFoundException, TransactionNotAllowedException {

		Optional<Account> accountOptional = searchAccount(transactionRequest.getAccountNumber());

		Account account = accountOptional.get();
		Double newAccountBalance = null;
		if (TransactionTypeEnum.DEPOSIT.getType().equals(transactionRequest.getTransactionType())
				&& transactionRequest.getAmount() > 0.01) {

			newAccountBalance = account.getAccountBalance() + transactionRequest.getAmount();
		} else if (TransactionTypeEnum.WITHDRAW.getType().equals(transactionRequest.getTransactionType())
				&& account.getAccountBalance() - transactionRequest.getAmount() > 0) {
			newAccountBalance = account.getAccountBalance() - transactionRequest.getAmount();
		} else {
			log.error("[ERROR] Operation not allowed for this account ", transactionRequest.getAccountNumber());
			throw new TransactionNotAllowedException(transactionRequest);
		}

		account.setAccountBalance(newAccountBalance);

		Transaction transaction = transactionService.updateAccount(transactionRequest, account);
		return new ResponseEntity<>(transaction, HttpStatus.CREATED);
	}

	@GetMapping(value = "/balance/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Return the account balance")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Account balance found"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Double> getAccountBalance(
			@PathVariable(name = "accountNumber", required = true) Integer accountNumber)
			throws AccountNotFoundException {

		log.info("[getAccountBalance] : account number : ", accountNumber);
		Optional<Account> accountOptional = searchAccount(accountNumber);
		Account account = accountOptional.get();

		Double balance = account.getAccountBalance();

		return ResponseEntity.status(HttpStatus.OK).body(balance);
	}

	@GetMapping(value = "/account/history", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List operations history for given account")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "History found") })
	public ResponseEntity<List<Transaction>> getTransactionsHistory(
			@RequestParam(value = "accountNumber", required = true) final int accountNumber)
			throws AccountNotFoundException {
		log.info("test");
		Optional<Account> accountOptional = searchAccount(accountNumber);
		Account account = accountOptional.get();

		Optional<List<Transaction>> transactionsOptional = transactionService.getAccountTransactionsHistory(account);
		if (!transactionsOptional.isPresent()) {
			log.error("[ERROR] Account with id %s not found ", accountNumber);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<Transaction>());
		}
		List<Transaction> transactionsHistory = transactionsOptional.get();

		return ResponseEntity.status(HttpStatus.OK).body(transactionsHistory);

	}

	private Optional<Account> searchAccount(final int accountNumber) throws AccountNotFoundException {
		Optional<Account> accountOptional = accountService.getAccount(accountNumber);
		if (!accountOptional.isPresent()) {
			log.error("[ERROR] Account with id %s not found ", accountNumber);
			throw new AccountNotFoundException(String.valueOf(accountNumber));
		}
		return accountOptional;
	}
}
