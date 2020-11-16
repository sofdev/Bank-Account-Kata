package fr.lcdlv.kata.steps;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.lcdlv.kata.controller.TransactionRequest;
import fr.lcdlv.kata.controller.TransactionTypeEnum;
import fr.lcdlv.kata.domain.Transaction;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TransactionsSteps extends SpringIntegrationTest {

	private static final String BASE_URL = "/bank/v1/";
	private Response response;

	private RequestSpecification request;

	@LocalServerPort
	private int port;

	Integer accountNumber;

	private static final ObjectMapper mapper = new ObjectMapper();

	@Given("as client with account number {int}")
	public void as_client_with_account_number(Integer accountNumber) {
		request = given().port(port).accept(ContentType.ANY);
		this.accountNumber = accountNumber;
	}

	@When("i want to deposit {double} euro to my account")
	public void i_want_to_deposit_euro_to_my_account(Double amount) throws JsonProcessingException {

		TransactionRequest transactionRequest = new TransactionRequest(accountNumber, amount,
				TransactionTypeEnum.DEPOSIT.getType());
		response = request.headers("content-type", ContentType.JSON).body(mapper.writeValueAsString(transactionRequest))
				.post("/bank/v1/transaction");
	}

	@Then("http response is {int}")
	public void http_response_is(Integer responseCode) {
		Assert.assertEquals(responseCode.intValue(), response.getStatusCode());
	}

	@And("response is")
	public void response_is(DataTable dataTable) {

		List<Map<String, String>> nested = dataTable.asMaps();
		Transaction created = response.then().extract().as(Transaction.class);
		String transactionType = nested.get(0).get("transaction_type");
		String transactionValue = nested.get(0).get("transaction_value");
		Assertions.assertAll(() -> Assertions.assertEquals(transactionType, created.getTransactionType()),
				() -> Assertions.assertEquals(Double.parseDouble(transactionValue), created.getTransactionValue()));

	}

	@When("i want to withdraw {double} euro from my account")
	public void i_want_to_withdraw_euro_from_my_account(Double amount) throws JsonProcessingException {
		TransactionRequest transactionRequest = new TransactionRequest(accountNumber, amount,
				TransactionTypeEnum.WITHDRAW.getType());
		response = request.headers("content-type", ContentType.JSON).body(mapper.writeValueAsString(transactionRequest))
				.post("/bank/v1/transaction");
	}

	@When("i want to consult my account balance")
	public void i_want_to_consult_my_account_balance() {
		response = request.when().get(BASE_URL.concat("balance/").concat(accountNumber.toString()));
	}

	@Then("result is displayed")
	public void result_is_displayed() {

		String responseString = response.then().assertThat().statusCode(HttpStatus.OK.value()).extract().asString();
		Assertions.assertNotNull(responseString);

	}

	@When("i want to display my account transactions history")
	public void i_want_to_display_my_account_transactions_history() {
		response = request.when()
				.get(BASE_URL.concat("account/history/").concat("?accountNumber=").concat(accountNumber.toString()));
	}

	@Then("message is {string}")
	public void message_is(String errorMessage) {

		String responseString = response.then().assertThat().statusCode(HttpStatus.NOT_FOUND.value()).extract()
				.asString();
		Assertions.assertAll(() -> Assertions.assertNotNull(responseString),
				() -> Assertions.assertEquals(errorMessage, responseString));

	}
}
