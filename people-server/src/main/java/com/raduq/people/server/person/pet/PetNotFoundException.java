package com.raduq.people.server.person.pet;

public class PetNotFoundException extends RuntimeException {

	public PetNotFoundException(Long id) {
		super(String.format("Pet with id %1$s not found", id));
	}
}
