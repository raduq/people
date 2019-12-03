package com.raduq.people.server.person;

public class PersonAlreadyExistException extends RuntimeException {

	public PersonAlreadyExistException(String firstName, String lastName) {
		super(String.format("Person with first name %1$s and last name %2$s already exists", firstName, lastName));
	}
}
