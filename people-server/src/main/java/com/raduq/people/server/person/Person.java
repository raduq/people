package com.raduq.people.server.person;


import com.raduq.people.server.person.address.Address;
import com.raduq.people.server.person.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	private Long id;
	private String firstName;
	private String lastName;
	private LocalDateTime birthDate;
	private Address address;
	private List<Pet> pets;

	public PersonEntity toEntity() {
		return new PersonMapper().toEntity(this);
	}
}
