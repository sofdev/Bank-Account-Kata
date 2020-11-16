
Feature: As a bank, a customer can display its account balance

	Background:
	Given as client with account number 1111
	

  Scenario: display account balance
    
    When i want to consult my account balance
    Then result is displayed

	
  Scenario: display account transactions history
    
    When i want to display my account transactions history
    Then result is displayed
    
  Scenario: display account transactions history account not found
	  Given as client with account number 2222
	  When i want to consult my account balance
	  Then http response is 404
	  And message is "Account number 2222 has not been found."
    