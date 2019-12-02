package com.raduq.people.server;

import com.raduq.people.server.person.Person;
import com.raduq.people.server.person.PersonEntity;
import com.raduq.people.server.person.address.Address;
import com.raduq.people.server.person.address.AddressEntity;
import com.raduq.people.server.person.pet.Pet;
import com.raduq.people.server.person.pet.PetEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestInstances {

	public Person person() {
		List<Pet> pets = new ArrayList<>();
		pets.add(pet());
		return Person.builder()
			.id(1L)
			.firstName("John")
			.lastName("Wayne")
			.birthDate(new Date())
			.address(address())
			.pets(pets)
			.build();
	}

	public PersonEntity personEntity() {
		List<PetEntity> pets = new ArrayList<>();
		pets.add(petEntity());
		return PersonEntity.builder()
			.id(1L)
			.firstName("John")
			.lastName("Wayne")
			.birthDate(new Date())
			.address(addressEntity())
			.pets(pets)
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

	public Pet pet() {
		return Pet.builder()
			.id(1L)
			.name("Toby")
			.age(2)
			.build();
	}

	public PetEntity petEntity() {
		return PetEntity.builder()
			.id(1L)
			.name("Toby")
			.age(2)
			.build();
	}
}
