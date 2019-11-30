package com.raduq.people.server.person.pet;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pet {
	private final Long id;
	private final String name;
	private final Integer age;

	public PetEntity toEntity() {
		return PetEntity.builder()
			.id(id)
			.name(name)
			.age(age)
			.build();
	}
}
