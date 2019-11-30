package com.raduq.people.server.person.pet;

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
}
