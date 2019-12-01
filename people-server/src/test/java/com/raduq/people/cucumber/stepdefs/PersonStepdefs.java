package com.raduq.people.cucumber.stepdefs;

import cucumber.api.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonStepdefs {

	@When("^the client calls /version$")
	public void the_client_issues_GET_version() throws Throwable{
		executeGet("http://localhost:8080/version");
	}

}
