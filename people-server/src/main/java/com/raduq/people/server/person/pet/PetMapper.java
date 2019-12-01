package com.raduq.people.server.person.pet;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public final class PetMapper {

	public final PetEntity toEntity(Pet pet) {
		return PetEntity.builder()
			.id(pet.getId())
			.name(pet.getName())
			.age(pet.getAge())
			.build();
	}

	public final Pet toDTO(PetEntity pet) {
		return Pet.builder()
			.id(pet.getId())
			.name(pet.getName())
			.age(pet.getAge())
			.build();
	}

	public final List<Pet> toListOfDTO(Spliterator<PetEntity> entities) {
		return StreamSupport.stream(entities, false)
			.map(PetEntity::toDTO)
			.collect(Collectors.toList());
	}
}
