package com.raduq.people.cucumber.stepdefs;

import com.raduq.people.server.person.address.Address;
import com.raduq.people.server.person.address.AddressRepository;
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


public class AddressStepdefs {

	private static final String LOCALHOST = "http://localhost";

	@LocalServerPort
	protected int port;

	@Autowired
	private AddressRepository repository;

	private ObjectMapper mapper = new ObjectMapper();
	private RestTemplate restTemplate = new RestTemplate();
	private ResponseEntity<Address> putReq;
	private ResponseEntity<Address> delReq;

	private String getUrl() {
		return LOCALHOST + ":" + port + "/api/v1/people/1/addresses";
	}

	@Given("the following address should exist:")
	public void createPet(Address address) {
		assertNotNull(repository.save(address.toEntity()));
	}

	@When("a GET request to address with id {int} should have status {int}")
	public void getRequestIsMade(Integer id, Integer status) {
		try {
			ResponseEntity<Address> response = restTemplate.exchange(getUrl() + "/" + id, HttpMethod.GET, null, Address.class);
			assertEquals(response.getStatusCodeValue(), status);
		} catch (HttpStatusCodeException e) {
			assertEquals(e.getRawStatusCode(), status);
		}
	}

	@When("a PUT request to address with id {int} with:")
	public void putRequestIsMade(Integer id, Address address) {
		try {
			putReq = restTemplate.exchange(getUrl() + "/" + id, HttpMethod.PUT, new HttpEntity<>(address), Address.class, id);
		} catch (HttpStatusCodeException e) {
			putReq = ResponseEntity.status(e.getRawStatusCode()).build();
		}
	}

	@When("a DELETE request to address with id {int}")
	public void deleteRequestIsMade(Integer id) {
		try {
			restTemplate.delete(getUrl() + "/" + id);
			delReq = ResponseEntity.ok().build();
		} catch (HttpStatusCodeException e) {
			delReq = ResponseEntity.status(e.getRawStatusCode()).build();
		}
	}

	@Then("the address PUT response should be:")
	public void putResponseIs(String jason) throws JsonProcessingException {
		String jsonBody = mapper.writeValueAsString(putReq.getBody());
		assertEquals(jason, jsonBody);
	}

	@Then("the address DELETE status should be {int}")
	public void delResponseIs(Integer status) {
		assertEquals(status, delReq.getStatusCodeValue());
	}

}
