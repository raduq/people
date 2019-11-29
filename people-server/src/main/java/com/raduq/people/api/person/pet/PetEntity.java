package com.raduq.people.api.person.pet;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@Entity(name = "pet")
public class PetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	private final String name;
	private final Integer age;

	public Pet toDTO() {
		return Pet.builder()
			.id(id)
			.name(name)
			.age(age)
			.build();
	}

}
