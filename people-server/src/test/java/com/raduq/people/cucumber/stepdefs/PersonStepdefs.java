package com.raduq.people.cucumber.stepdefs;

import com.raduq.people.cucumber.CucumberTest;
import com.raduq.people.server.person.Person;
import com.raduq.people.server.person.PersonRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonInclude;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class PersonStepdefs extends CucumberTest {

	private static final String LOCALHOST = "http://localhost";

	@LocalServerPort
	protected int port;

	@Autowired
	private PersonRepository repository;

	private ObjectMapper mapper = new ObjectMapper();
	private RestTemplate restTemplate = new RestTemplate();
	private Map<String, ResponseEntity<Person>> params = new HashMap<>();

	private String getUrl() {
		return LOCALHOST + ":" + port + "/api/v1/people";
	}

	@Before
	public void setUp() throws Exception {
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	@Given("the following person should exist:")
	public void createPerson(Person person) {
		assertNotNull(repository.save(person.toEntity()));
	}

	@When("a GET request to person with id {int} named {word}")
	public void getRequestIsMade(Integer id, String requestName) {
		ResponseEntity<Person> response = restTemplate.getForEntity(getUrl() + "/" + id, Person.class);
		params.put(requestName, response);
	}

	@Then("the request {word} should have status code {int}")
	public void statusCodeIs(String requestName, Integer status) {
		assertEquals(status, params.get(requestName).getStatusCodeValue());
	}

	@Then("the request {word} should respond with:")
	public void responseIs(String requestName, String personJson) throws JsonProcessingException {
		String jsonBody = mapper.writeValueAsString(params.get(requestName).getBody());
		assertEquals(personJson, jsonBody);
	}

}
