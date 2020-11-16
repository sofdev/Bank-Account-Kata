# Bank Account Kata
Bank Account Kata REST spring boot application. this application allows: 
  - Deposit money
  - Withdraw money
  - Display account balance
  - Display account transactions history
 
This project was created with Spring Boot and use an embedded database (h2), and JDK 11
# App startup
The application can be started by the following maven command:
> mvn package && java -jar target/Bank-Account-Kata-0.0.1-SNAPSHOT.jar

The app implements Unit test using Junit 5 and BDD using cucumber 6.8.0
To execute the tests:
>  mvn test

# API
This application expose those rest endpoints: 
- POST /bank/v1/transaction/ : lets us to execute a transaction (Desposit or withdraw)
- GET /bank/v1/balance/<accountNumber> : to check account balance
- GET /bank/v1/account/history : to view the history of transactions 

for more details, When the server is running you can access the API Swagger at this URL:  
	http://localhost:8081/swagger-ui/

a default user is created in h2 database with id 1111.

