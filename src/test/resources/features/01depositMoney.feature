Feature: As a bank, deposit money from a customer to his account, is allowed when superior to € 0.01

	Background:

		Given as client with account number 1111
		

 	Scenario: deposit 10.0 euro to my account
		When i want to deposit 10 euro to my account
		Then http response is 201
		And response is
			| transaction_type    |  transaction_value  |
			| Deposit             |           10.0			|
	

 	Scenario: deposit 0.001 euro to my account
		When i want to deposit 0.001 euro to my account
		Then http response is 405