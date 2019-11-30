package com.raduq.people.server.person.address;

public class AddressNotFoundException extends RuntimeException {

	public AddressNotFoundException(Long id) {
		super(String.format("Address with id %1$s not found", id));
	}
}
