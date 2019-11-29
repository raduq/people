package com.raduq.people.api.person;

public class PersonNotFoundException extends RuntimeException {

	public PersonNotFoundException(Long id) {
		super(String.format("Person with id %1$s not found", id));
	}
}
