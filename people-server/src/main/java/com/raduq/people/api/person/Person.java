package com.raduq.people.api.person;


import com.raduq.people.api.person.address.Address;
import com.raduq.people.api.person.pet.Pet;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Person {
	private final Long id;
	private final String firstName;
	private final String lastName;
	private final LocalDateTime birthDate;
	private final Address address;
	private final List<Pet> pets;

	public PersonEntity toEntity() {
		return PersonEntity.builder()
			.id(id)
			.firstName(firstName)
			.lastName(lastName)
			.birthDate(birthDate)
			.address(address.toEntity())
			.build();
	}
}
