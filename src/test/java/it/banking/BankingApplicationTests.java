package it.banking;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class BankingApplicationTests {

	@Test
	public void getBalance(){

		given().header("Content-Type", "application/xml")
			   .header("Api-Key", "application/xml")
			   .header("Auth-Schema", "application/xml")
			   .queryParam("accountId", 14537780)
			   .when().get(" http://localhost:8082/banking/balance")
			   .then().statusCode(200);

	}

	@Test
	public void getTransactions(){

		given().header("Content-Type", "application/xml")
			   .header("Api-Key", "application/xml")
			   .header("Auth-Schema", "application/xml")
			   .queryParam("accountId", 14537780)
			   .queryParam("fromAccountingDate", "2019-01-01")
			   .queryParam("toAccountingDate", "2019-12-01")
			   .when()
			   .get(" http://localhost:8082/banking/transactions")
			   .then().statusCode(200);

	}

	@Test
	public void createMoneyTransfer(){

		given().header("Content-Type", "application/xml")
			   .header("Api-Key", "application/xml")
			   .header("Auth-Schema", "application/xml")
			   .formParam("accountId", 14537780)
			   .formParam("receiverName", "")
			   .formParam("description", "Test moneyTransfer")
			   .formParam("currency", "EUR")
			   .formParam("amount", "10.0")
			   .formParam("executionDate", "2021-06-09")
			   .when()
			   .post("http://localhost:8082/banking/createMoneyTransfer")
		       .then().statusCode(200);
	}


}
