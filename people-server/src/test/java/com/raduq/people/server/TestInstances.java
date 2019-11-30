package com.raduq.people.server;

import com.raduq.people.server.person.Person;
import com.raduq.people.server.person.PersonEntity;
import com.raduq.people.server.person.address.Address;
import com.raduq.people.server.person.address.AddressEntity;

import java.time.LocalDateTime;

public class TestInstances {

	public Person person() {
		return Person.builder()
			.id(1L)
			.firstName("John")
			.lastName("Wayne")
			.birthDate(LocalDateTime.of(1988, 12, 23, 6, 0, 0))
			.address(address())
			.build();
	}

	public PersonEntity personEntity() {
		return PersonEntity.builder()
			.id(1L)
			.firstName("John")
			.lastName("Wayne")
			.birthDate(LocalDateTime.of(1988, 12, 23, 6, 0, 0))
			.address(addressEntity())
			.build();
	}

	public Address address() {
		return Address.builder()
			.id(1L)
			.street("Alexanderplein")
			.number(3L)
			.numberAddition(1L)
			.zipCode("1234 CB")
			.city("Amsterdam")
			.country("Netherlands")
			.build();
	}

	public AddressEntity addressEntity() {
		return AddressEntity.builder()
			.id(1L)
			.street("Alexanderplein")
			.number(3L)
			.numberAddition(1L)
			.zipCode("1234 CB")
			.city("Amsterdam")
			.country("Netherlands")
			.build();
	}
}
