package fr.lcdlv.kata.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import fr.lcdlv.kata.exceptions.AccountNotFoundException;
import fr.lcdlv.kata.exceptions.TransactionNotAllowedException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ AccountNotFoundException.class})
	private ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException exception, WebRequest request) {

		log.error(exception.getMessage());
		return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND,
				request);
	}
	
	@ExceptionHandler({TransactionNotAllowedException.class })
	private ResponseEntity<Object> handleTransactionNotAllowedException(TransactionNotAllowedException exception, WebRequest request) {

		log.error(exception.getMessage());
		return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED,
				request);
	}

}