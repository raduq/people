package com.raduq.people.cucumber.stepdefs;

import com.raduq.people.server.person.pet.Pet;
import com.raduq.people.server.person.pet.PetRepository;
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


public class PetStepdefs {

	private static final String LOCALHOST = "http://localhost";

	@LocalServerPort
	protected int port;

	@Autowired
	private PetRepository repository;

	private ObjectMapper mapper = new ObjectMapper();
	private RestTemplate restTemplate = new RestTemplate();
	private ResponseEntity<Pet> postReq;
	private ResponseEntity<Pet> putReq;
	private ResponseEntity<Pet> delReq;

	private String getUrl() {
		return LOCALHOST + ":" + port + "/api/v1/people/1/pets";
	}

	@Given("the following pet should exist:")
	public void createPet(Pet pet) {
		assertNotNull(repository.save(pet.toEntity()));
	}

	@When("a GET request to all pet should return some pet")
	public void getAllIsMade() {
		ResponseEntity<Pet[]> response = restTemplate.getForEntity(getUrl(), Pet[].class);
		assertTrue(response.getBody().length > 0);
	}

	@When("a GET request to pet with id {int} should have status {int}")
	public void getRequestIsMade(Integer id, Integer status) {
		try {
			ResponseEntity<Pet> response = restTemplate.exchange(getUrl() + "/" + id, HttpMethod.GET, null, Pet.class);
			assertEquals(response.getStatusCodeValue(), status);
		} catch (HttpStatusCodeException e) {
			assertEquals(e.getRawStatusCode(), status);
		}
	}

	@When("a POST request to pet with:")
	public void postRequestIsMade(Pet pet) {
		try {
			postReq = restTemplate.postForEntity(getUrl(), pet, Pet.class);
		} catch (HttpStatusCodeException e) {
			postReq = ResponseEntity.status(e.getRawStatusCode()).build();
		}
	}

	@When("a PUT request to pet with id {int} with:")
	public void putRequestIsMade(Integer id, Pet pet) {
		try {
			putReq = restTemplate.exchange(getUrl() + "/" + id, HttpMethod.PUT, new HttpEntity<>(pet), Pet.class, id);
		} catch (HttpStatusCodeException e) {
			putReq = ResponseEntity.status(e.getRawStatusCode()).build();
		}
	}

	@When("a DELETE request to pet with id {int}")
	public void deleteRequestIsMade(Integer id) {
		try {
			restTemplate.delete(getUrl() + "/" + id);
			delReq = ResponseEntity.ok().build();
		} catch (HttpStatusCodeException e) {
			delReq = ResponseEntity.status(e.getRawStatusCode()).build();
		}
	}

	@Then("the pet POST response should be:")
	public void postResponseIs(String jason) throws JsonProcessingException {
		String jsonBody = mapper.writeValueAsString(postReq.getBody());
		assertEquals(jason, jsonBody);
	}

	@Then("the pet PUT response should be:")
	public void putResponseIs(String jason) throws JsonProcessingException {
		String jsonBody = mapper.writeValueAsString(putReq.getBody());
		assertEquals(jason, jsonBody);
	}

	@Then("the pet DELETE status should be {int}")
	public void delResponseIs(Integer status) {
		assertEquals(status, delReq.getStatusCodeValue());
	}

}
