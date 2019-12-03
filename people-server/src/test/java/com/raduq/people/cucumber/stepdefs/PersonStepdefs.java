package com.raduq.people.cucumber.stepdefs;

import com.raduq.people.cucumber.CucumberTest;
import com.raduq.people.server.person.Person;
import com.raduq.people.server.person.PersonRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PersonStepdefs extends CucumberTest {

	private static final String LOCALHOST = "http://localhost";

	@LocalServerPort
	protected int port;

	@Autowired
	private PersonRepository repository;

	private ObjectMapper mapper = new ObjectMapper();
	private RestTemplate restTemplate = new RestTemplate();
	private ResponseEntity<Person> postReq;
	private ResponseEntity<Person> putReq;
	private ResponseEntity<Person> delReq;

	private String getUrl() {
		return LOCALHOST + ":" + port + "/api/v1/people";
	}

	@Given("the following person should exist:")
	public void createPerson(Person person) {
		assertNotNull(repository.save(person.toEntity()));
	}

	@When("a GET request to all people should return some person")
	public void getAllIsMade() {
		ResponseEntity<Person[]> response = restTemplate.getForEntity(getUrl(), Person[].class);
		assertTrue(response.getBody().length > 0);
	}

	@When("a GET request to person with firstName {word} should return {int} people")
	public void getFirstNameRequestIsMade(String firstName, Integer num) {
		ResponseEntity<Person[]> response = restTemplate.getForEntity(getUrl() + "?first-name=" + firstName, Person[].class);
		assertEquals(response.getBody().length, num);
	}

	@When("a GET request to person with lastName {word} should return {int} people")
	public void getLastNameRequestIsMade(String lastName, Integer num) {
		ResponseEntity<Person[]> response = restTemplate.getForEntity(getUrl() + "?last-name=" + lastName, Person[].class);
		assertEquals(response.getBody().length, num);
	}

	@When("a GET request to person with id {int} should have status {int}")
	public void getRequestIsMade(Integer id, Integer status) {
		try {
			ResponseEntity<Person> response = restTemplate.exchange(getUrl() + "/" + id, HttpMethod.GET, null, Person.class);
			assertEquals(response.getStatusCodeValue(), status);
		} catch (HttpStatusCodeException e) {
			assertEquals(status, e.getRawStatusCode());
		}
	}

	@When("a POST request to person with:")
	public void postRequestIsMade(Person person) {
		try {
			postReq = restTemplate.postForEntity(getUrl(), person, Person.class);
		} catch (HttpStatusCodeException e) {
			postReq = ResponseEntity.status(e.getRawStatusCode()).build();
		}
	}

	@When("a PUT request to person with id {int} with:")
	public void putRequestIsMade(Integer id, Person person) {
		try {
			putReq = restTemplate.exchange(getUrl() + "/" + id, HttpMethod.PUT, new HttpEntity<>(person), Person.class, id);
		} catch (HttpStatusCodeException e) {
			putReq = ResponseEntity.status(e.getRawStatusCode()).build();
		}
	}

	@When("a DELETE request to person with id {int}")
	public void deleteRequestIsMade(Integer id) {
		try {
			restTemplate.delete(getUrl() + "/" + id);
			delReq = ResponseEntity.ok().build();
		} catch (HttpStatusCodeException e) {
			delReq = ResponseEntity.status(e.getRawStatusCode()).build();
		}
	}

	@Then("the person POST response should be:")
	public void postResponseIs(String personJson) throws JsonProcessingException {
		String jsonBody = mapper.writeValueAsString(postReq.getBody());
		assertEquals(personJson, jsonBody);
	}

	@Then("the person PUT response should be:")
	public void putResponseIs(String personJson) throws JsonProcessingException {
		String jsonBody = mapper.writeValueAsString(putReq.getBody());
		assertEquals(personJson, jsonBody);
	}

	@Then("the person DELETE status should be {int}")
	public void delResponseIs(Integer status) {
		assertEquals(status, delReq.getStatusCodeValue());
	}

}
