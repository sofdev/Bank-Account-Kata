Feature: As a bank, withdraw money from a customer account, is allowed when no overdraft used

	Background:

		Given as client with account number 1111
		


 	Scenario: withdraw 50.0 euro from my account
		When i want to withdraw 50.0 euro from my account
		Then http response is 201
		And response is
			| transaction_type    |  transaction_value  |
			| Withdraw            |           50.0			|
	

 	Scenario: withdraw 2000.0 euro from my account
		When i want to withdraw 2000.0 euro from my account
		Then http response is 405